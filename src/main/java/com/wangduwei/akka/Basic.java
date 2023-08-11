package com.wangduwei.akka;

//import akka.actor.ActorRef;
//import akka.actor.ActorSystem;
//import akka.actor.Props;
//import akka.actor.UntypedActor;
//import akka.dispatch.OnComplete;
//import akka.pattern.Patterns;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import scala.concurrent.Future;
//
//import java.util.concurrent.CountDownLatch;

///
//
//https://www.cnblogs.com/Aitozi/p/15704987.html
//在这个例子中简单模拟了并发加减操作
//
//public class Basic {
//
//    public static void main(String[] args) {
//
//        final ActorSystem actorSystem = ActorSystem.create("actor-system");
//
//        final ActorRef actorRef = actorSystem.actorOf(Props.create(BankActor.class), "bank-actor");
//
//        CountDownLatch addCount = new CountDownLatch(20);
//        CountDownLatch minusCount = new CountDownLatch(10);
//
//        Thread addCountT = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (addCount.getCount() > 0) {
//                    actorRef.tell(Command.ADD, null);
//                    addCount.countDown();
//                }
//            }
//        });
//
//        Thread minusCountT = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (minusCount.getCount() > 0) {
//                    actorRef.tell(Command.MINUS, null);
//                    minusCount.countDown();
//                }
//            }
//        });
//
//        minusCountT.start();
//        addCountT.start();
//        minusCount.await();
//        addCount.await();
//
//        Future<Object> count = Patterns.ask(actorRef, Command.GET, 1000);
//        count.onComplete(
//                new OnComplete<Object>() {
//                    @Override
//                    public void onComplete(Throwable failure, Object success) throws Throwable {
//                        if (failure != null) {
//                            failure.printStackTrace();
//                        } else {
//                            log.info("Get result from " + success);
//                        }
//                    }
//                },
//                Executors.directExecutionContext());
//        actorSystem.shutdown();
//
//
//    }
//
//
//}
//
//
//public class BankActor extends UntypedActor {
//
//    private static final Logger log = LoggerFactory.getLogger(BankActor.class);
//    private int count;
//
//    @Override
//    public void preStart() throws Exception, Exception {
//        super.preStart();
//        count = 0;
//    }
//
//    @Override
//    public void onReceive(Object message) throws Throwable {
//        // 可以使用枚举或者动态代理类来实现方法调用
//        if (message instanceof Command) {
//            Command cmd = (Command) message;
//            switch (cmd) {
//                case ADD:
//                    log.info("Add 1 from {} to {}", count, ++count);
//                    break;
//                case MINUS:
//                    log.info("Minus 1 from {} to {}", count, --count);
//                    break;
//                case GET:
//                    log.info("Return current count " + getSender());
//                    getSender().tell(count, this.getSelf());
//                    break;
//                default:
//                    log.warn("UnSupport cmd: " + cmd);
//            }
//        } else {
//            log.warn("Discard unknown message: {}", message);
//        }
//    }
//}
//
//enum Command {
//    ADD,
//    MINUS,
//    GET
//}