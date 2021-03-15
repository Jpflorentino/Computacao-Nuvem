package rpcstubs;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Os 4 casos de definição de serviço
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: rpcService.proto")
public final class BaseServiceGrpc {

  private BaseServiceGrpc() {}

  public static final String SERVICE_NAME = "baseservice.BaseService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase1Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "case1",
      requestType = rpcstubs.Request.class,
      responseType = rpcstubs.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase1Method() {
    io.grpc.MethodDescriptor<rpcstubs.Request, rpcstubs.Reply> getCase1Method;
    if ((getCase1Method = BaseServiceGrpc.getCase1Method) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getCase1Method = BaseServiceGrpc.getCase1Method) == null) {
          BaseServiceGrpc.getCase1Method = getCase1Method = 
              io.grpc.MethodDescriptor.<rpcstubs.Request, rpcstubs.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "case1"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("case1"))
                  .build();
          }
        }
     }
     return getCase1Method;
  }

  private static volatile io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "case2",
      requestType = rpcstubs.Request.class,
      responseType = rpcstubs.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase2Method() {
    io.grpc.MethodDescriptor<rpcstubs.Request, rpcstubs.Reply> getCase2Method;
    if ((getCase2Method = BaseServiceGrpc.getCase2Method) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getCase2Method = BaseServiceGrpc.getCase2Method) == null) {
          BaseServiceGrpc.getCase2Method = getCase2Method = 
              io.grpc.MethodDescriptor.<rpcstubs.Request, rpcstubs.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "case2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("case2"))
                  .build();
          }
        }
     }
     return getCase2Method;
  }

  private static volatile io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase3Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "case3",
      requestType = rpcstubs.Request.class,
      responseType = rpcstubs.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase3Method() {
    io.grpc.MethodDescriptor<rpcstubs.Request, rpcstubs.Reply> getCase3Method;
    if ((getCase3Method = BaseServiceGrpc.getCase3Method) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getCase3Method = BaseServiceGrpc.getCase3Method) == null) {
          BaseServiceGrpc.getCase3Method = getCase3Method = 
              io.grpc.MethodDescriptor.<rpcstubs.Request, rpcstubs.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "case3"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("case3"))
                  .build();
          }
        }
     }
     return getCase3Method;
  }

  private static volatile io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase4Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "case4",
      requestType = rpcstubs.Request.class,
      responseType = rpcstubs.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<rpcstubs.Request,
      rpcstubs.Reply> getCase4Method() {
    io.grpc.MethodDescriptor<rpcstubs.Request, rpcstubs.Reply> getCase4Method;
    if ((getCase4Method = BaseServiceGrpc.getCase4Method) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getCase4Method = BaseServiceGrpc.getCase4Method) == null) {
          BaseServiceGrpc.getCase4Method = getCase4Method = 
              io.grpc.MethodDescriptor.<rpcstubs.Request, rpcstubs.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "case4"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("case4"))
                  .build();
          }
        }
     }
     return getCase4Method;
  }

  private static volatile io.grpc.MethodDescriptor<rpcstubs.Void,
      rpcstubs.Reply> getPingServerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pingServer",
      requestType = rpcstubs.Void.class,
      responseType = rpcstubs.Reply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rpcstubs.Void,
      rpcstubs.Reply> getPingServerMethod() {
    io.grpc.MethodDescriptor<rpcstubs.Void, rpcstubs.Reply> getPingServerMethod;
    if ((getPingServerMethod = BaseServiceGrpc.getPingServerMethod) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getPingServerMethod = BaseServiceGrpc.getPingServerMethod) == null) {
          BaseServiceGrpc.getPingServerMethod = getPingServerMethod = 
              io.grpc.MethodDescriptor.<rpcstubs.Void, rpcstubs.Reply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "pingServer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Void.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Reply.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("pingServer"))
                  .build();
          }
        }
     }
     return getPingServerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<rpcstubs.News,
      rpcstubs.Void> getPublishNewsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "publishNews",
      requestType = rpcstubs.News.class,
      responseType = rpcstubs.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<rpcstubs.News,
      rpcstubs.Void> getPublishNewsMethod() {
    io.grpc.MethodDescriptor<rpcstubs.News, rpcstubs.Void> getPublishNewsMethod;
    if ((getPublishNewsMethod = BaseServiceGrpc.getPublishNewsMethod) == null) {
      synchronized (BaseServiceGrpc.class) {
        if ((getPublishNewsMethod = BaseServiceGrpc.getPublishNewsMethod) == null) {
          BaseServiceGrpc.getPublishNewsMethod = getPublishNewsMethod = 
              io.grpc.MethodDescriptor.<rpcstubs.News, rpcstubs.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "baseservice.BaseService", "publishNews"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.News.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  rpcstubs.Void.getDefaultInstance()))
                  .setSchemaDescriptor(new BaseServiceMethodDescriptorSupplier("publishNews"))
                  .build();
          }
        }
     }
     return getPublishNewsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BaseServiceStub newStub(io.grpc.Channel channel) {
    return new BaseServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BaseServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BaseServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BaseServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BaseServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Os 4 casos de definição de serviço
   * </pre>
   */
  public static abstract class BaseServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void case1(rpcstubs.Request request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(getCase1Method(), responseObserver);
    }

    /**
     */
    public void case2(rpcstubs.Request request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(getCase2Method(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<rpcstubs.Request> case3(
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      return asyncUnimplementedStreamingCall(getCase3Method(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<rpcstubs.Request> case4(
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      return asyncUnimplementedStreamingCall(getCase4Method(), responseObserver);
    }

    /**
     * <pre>
     * Utilização de parâmetro e retorno Void
     * </pre>
     */
    public void pingServer(rpcstubs.Void request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncUnimplementedUnaryCall(getPingServerMethod(), responseObserver);
    }

    /**
     */
    public void publishNews(rpcstubs.News request,
        io.grpc.stub.StreamObserver<rpcstubs.Void> responseObserver) {
      asyncUnimplementedUnaryCall(getPublishNewsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCase1Method(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcstubs.Request,
                rpcstubs.Reply>(
                  this, METHODID_CASE1)))
          .addMethod(
            getCase2Method(),
            asyncServerStreamingCall(
              new MethodHandlers<
                rpcstubs.Request,
                rpcstubs.Reply>(
                  this, METHODID_CASE2)))
          .addMethod(
            getCase3Method(),
            asyncClientStreamingCall(
              new MethodHandlers<
                rpcstubs.Request,
                rpcstubs.Reply>(
                  this, METHODID_CASE3)))
          .addMethod(
            getCase4Method(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                rpcstubs.Request,
                rpcstubs.Reply>(
                  this, METHODID_CASE4)))
          .addMethod(
            getPingServerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcstubs.Void,
                rpcstubs.Reply>(
                  this, METHODID_PING_SERVER)))
          .addMethod(
            getPublishNewsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                rpcstubs.News,
                rpcstubs.Void>(
                  this, METHODID_PUBLISH_NEWS)))
          .build();
    }
  }

  /**
   * <pre>
   * Os 4 casos de definição de serviço
   * </pre>
   */
  public static final class BaseServiceStub extends io.grpc.stub.AbstractStub<BaseServiceStub> {
    private BaseServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BaseServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BaseServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BaseServiceStub(channel, callOptions);
    }

    /**
     */
    public void case1(rpcstubs.Request request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCase1Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void case2(rpcstubs.Request request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getCase2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<rpcstubs.Request> case3(
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCase3Method(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<rpcstubs.Request> case4(
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getCase4Method(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Utilização de parâmetro e retorno Void
     * </pre>
     */
    public void pingServer(rpcstubs.Void request,
        io.grpc.stub.StreamObserver<rpcstubs.Reply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingServerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void publishNews(rpcstubs.News request,
        io.grpc.stub.StreamObserver<rpcstubs.Void> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPublishNewsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Os 4 casos de definição de serviço
   * </pre>
   */
  public static final class BaseServiceBlockingStub extends io.grpc.stub.AbstractStub<BaseServiceBlockingStub> {
    private BaseServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BaseServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BaseServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BaseServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public rpcstubs.Reply case1(rpcstubs.Request request) {
      return blockingUnaryCall(
          getChannel(), getCase1Method(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<rpcstubs.Reply> case2(
        rpcstubs.Request request) {
      return blockingServerStreamingCall(
          getChannel(), getCase2Method(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Utilização de parâmetro e retorno Void
     * </pre>
     */
    public rpcstubs.Reply pingServer(rpcstubs.Void request) {
      return blockingUnaryCall(
          getChannel(), getPingServerMethod(), getCallOptions(), request);
    }

    /**
     */
    public rpcstubs.Void publishNews(rpcstubs.News request) {
      return blockingUnaryCall(
          getChannel(), getPublishNewsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Os 4 casos de definição de serviço
   * </pre>
   */
  public static final class BaseServiceFutureStub extends io.grpc.stub.AbstractStub<BaseServiceFutureStub> {
    private BaseServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BaseServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BaseServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BaseServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcstubs.Reply> case1(
        rpcstubs.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getCase1Method(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Utilização de parâmetro e retorno Void
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcstubs.Reply> pingServer(
        rpcstubs.Void request) {
      return futureUnaryCall(
          getChannel().newCall(getPingServerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<rpcstubs.Void> publishNews(
        rpcstubs.News request) {
      return futureUnaryCall(
          getChannel().newCall(getPublishNewsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CASE1 = 0;
  private static final int METHODID_CASE2 = 1;
  private static final int METHODID_PING_SERVER = 2;
  private static final int METHODID_PUBLISH_NEWS = 3;
  private static final int METHODID_CASE3 = 4;
  private static final int METHODID_CASE4 = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BaseServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BaseServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CASE1:
          serviceImpl.case1((rpcstubs.Request) request,
              (io.grpc.stub.StreamObserver<rpcstubs.Reply>) responseObserver);
          break;
        case METHODID_CASE2:
          serviceImpl.case2((rpcstubs.Request) request,
              (io.grpc.stub.StreamObserver<rpcstubs.Reply>) responseObserver);
          break;
        case METHODID_PING_SERVER:
          serviceImpl.pingServer((rpcstubs.Void) request,
              (io.grpc.stub.StreamObserver<rpcstubs.Reply>) responseObserver);
          break;
        case METHODID_PUBLISH_NEWS:
          serviceImpl.publishNews((rpcstubs.News) request,
              (io.grpc.stub.StreamObserver<rpcstubs.Void>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CASE3:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.case3(
              (io.grpc.stub.StreamObserver<rpcstubs.Reply>) responseObserver);
        case METHODID_CASE4:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.case4(
              (io.grpc.stub.StreamObserver<rpcstubs.Reply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BaseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BaseServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return rpcstubs.RpcService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BaseService");
    }
  }

  private static final class BaseServiceFileDescriptorSupplier
      extends BaseServiceBaseDescriptorSupplier {
    BaseServiceFileDescriptorSupplier() {}
  }

  private static final class BaseServiceMethodDescriptorSupplier
      extends BaseServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BaseServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BaseServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BaseServiceFileDescriptorSupplier())
              .addMethod(getCase1Method())
              .addMethod(getCase2Method())
              .addMethod(getCase3Method())
              .addMethod(getCase4Method())
              .addMethod(getPingServerMethod())
              .addMethod(getPublishNewsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
