package listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryTransformer implements IAnnotationTransformer {

    private static final int MAX_RETRY = 2; //

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }

    // RetryAnalyzer class
    public static class RetryAnalyzer implements IRetryAnalyzer {
        private int retryCount = 0;

        @Override
        public boolean retry(ITestResult result) {
            if (retryCount < MAX_RETRY) {
                retryCount++;
                return true;
            }
            return false;
        }
    }
}
