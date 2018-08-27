package SimpleMR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MWRejection implements RejectedExecutionHandler {
    private final static Logger logger = LoggerFactory.getLogger(MWRejection.class);
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        logger.error("{}","该Master线程已超负荷");
        throw new RejectedExecutionException();
    }
}