package org.example;

import org.apache.jmeter.control.LoopController;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.extractor.json.jsonpath.JSONPostProcessor;
import org.apache.jmeter.protocol.http.control.HeaderManager;
import org.apache.jmeter.protocol.http.sampler.HTTPSamplerProxy;
import org.apache.jmeter.reporters.Summariser;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.testelement.TestPlan;
import org.apache.jmeter.threads.ThreadGroup;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;
import org.apache.jmeter.protocol.http.control.Header;
import org.apache.jorphan.collections.ListedHashTree;

import java.io.FileOutputStream;

public class JMeterLoginTokenTest {

    public static void main(String[] args) throws Exception {

        // 1. Create JMeter Engine
        StandardJMeterEngine jmeter = new StandardJMeterEngine();

        // 2. HTTP Sampler - Login
        HTTPSamplerProxy loginSampler = new HTTPSamplerProxy();
        loginSampler.setDomain("reqres.in");
        loginSampler.setPort(443);
        loginSampler.setProtocol("https");
        loginSampler.setPath("/api/login");
        loginSampler.setMethod("POST");
        loginSampler.addNonEncodedArgument("", "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }", "");
        loginSampler.setPostBodyRaw(true);
        loginSampler.setName("POST Login Request");

        // 3. Header Manager for login
        HeaderManager loginHeaders = new HeaderManager();
        loginHeaders.add(new Header("Content-Type", "application/json"));
        loginSampler.setHeaderManager(loginHeaders);

        // 4. Extract Token
        JSONPostProcessor jsonExtractor = new JSONPostProcessor();
        jsonExtractor.setName("Extract Token");
        jsonExtractor.setJsonPathExpressions("authToken=$.token");

        // 5. HTTP Sampler - Protected GET
        HTTPSamplerProxy getSampler = new HTTPSamplerProxy();
        getSampler.setDomain("reqres.in");
        getSampler.setPort(443);
        getSampler.setProtocol("https");
        getSampler.setPath("/api/users/2");
        getSampler.setMethod("GET");
        getSampler.setName("GET User with Token");

        // 6. Header Manager for GET
        HeaderManager getHeaders = new HeaderManager();
        getHeaders.add(new Header("Authorization", "Bearer ${authToken}"));
        getSampler.setHeaderManager(getHeaders);

        // 7. Loop Controller
        LoopController loopController = new LoopController();
        loopController.setLoops(1);
        loopController.setFirst(true);
        loopController.initialize();

        // 8. Thread Group
        ThreadGroup threadGroup = new ThreadGroup();
        threadGroup.setName("User Thread Group");
        threadGroup.setNumThreads(1);
        threadGroup.setRampUp(1);
        threadGroup.setSamplerController(loopController);

        // 9. Test Plan
        TestPlan testPlan = new TestPlan("Login + Token Test Plan");

        // 10. Build Test Plan tree
        HashTree testPlanTree = new HashTree();

        HashTree threadGroupHashTree = testPlanTree.add(testPlan, threadGroup);

        HashTree loginTree = threadGroupHashTree.add(loginSampler);
        loginTree.add(loginHeaders);
        loginTree.add(jsonExtractor);

        HashTree getTree = threadGroupHashTree.add(getSampler);
        getTree.add(getHeaders);

        // 11. Save test plan to JMX (optional, for debugging)
        SaveService.saveTree(testPlanTree, new FileOutputStream("login_token_test.jmx"));

        // 12. Run Test Plan
        jmeter.configure(testPlanTree);
        jmeter.run();

        System.out.println("✅ Test plan executed. Check results/logs.");
    }
    }

