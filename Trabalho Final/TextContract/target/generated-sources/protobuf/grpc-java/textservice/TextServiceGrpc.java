package textservice;

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
 * The greeting service definition
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.18.0)",
    comments = "Source: TextService.proto")
public final class TextServiceGrpc {

  private TextServiceGrpc() {}

  public static final String SERVICE_NAME = "textservice.TextService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<textservice.LoginRequest,
      textservice.LoginReply> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "login",
      requestType = textservice.LoginRequest.class,
      responseType = textservice.LoginReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<textservice.LoginRequest,
      textservice.LoginReply> getLoginMethod() {
    io.grpc.MethodDescriptor<textservice.LoginRequest, textservice.LoginReply> getLoginMethod;
    if ((getLoginMethod = TextServiceGrpc.getLoginMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getLoginMethod = TextServiceGrpc.getLoginMethod) == null) {
          TextServiceGrpc.getLoginMethod = getLoginMethod = 
              io.grpc.MethodDescriptor.<textservice.LoginRequest, textservice.LoginReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.LoginRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.LoginReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("login"))
                  .build();
          }
        }
     }
     return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<textservice.LogOutRequest,
      textservice.LogOutReply> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "logout",
      requestType = textservice.LogOutRequest.class,
      responseType = textservice.LogOutReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<textservice.LogOutRequest,
      textservice.LogOutReply> getLogoutMethod() {
    io.grpc.MethodDescriptor<textservice.LogOutRequest, textservice.LogOutReply> getLogoutMethod;
    if ((getLogoutMethod = TextServiceGrpc.getLogoutMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getLogoutMethod = TextServiceGrpc.getLogoutMethod) == null) {
          TextServiceGrpc.getLogoutMethod = getLogoutMethod = 
              io.grpc.MethodDescriptor.<textservice.LogOutRequest, textservice.LogOutReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.LogOutRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.LogOutReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("logout"))
                  .build();
          }
        }
     }
     return getLogoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<textservice.ImageRequest,
      textservice.ImageReply> getSendImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendImage",
      requestType = textservice.ImageRequest.class,
      responseType = textservice.ImageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<textservice.ImageRequest,
      textservice.ImageReply> getSendImageMethod() {
    io.grpc.MethodDescriptor<textservice.ImageRequest, textservice.ImageReply> getSendImageMethod;
    if ((getSendImageMethod = TextServiceGrpc.getSendImageMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getSendImageMethod = TextServiceGrpc.getSendImageMethod) == null) {
          TextServiceGrpc.getSendImageMethod = getSendImageMethod = 
              io.grpc.MethodDescriptor.<textservice.ImageRequest, textservice.ImageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "sendImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ImageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ImageReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("sendImage"))
                  .build();
          }
        }
     }
     return getSendImageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<textservice.ORCRequest,
      textservice.ORCReply> getDetectTextMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "detectText",
      requestType = textservice.ORCRequest.class,
      responseType = textservice.ORCReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<textservice.ORCRequest,
      textservice.ORCReply> getDetectTextMethod() {
    io.grpc.MethodDescriptor<textservice.ORCRequest, textservice.ORCReply> getDetectTextMethod;
    if ((getDetectTextMethod = TextServiceGrpc.getDetectTextMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getDetectTextMethod = TextServiceGrpc.getDetectTextMethod) == null) {
          TextServiceGrpc.getDetectTextMethod = getDetectTextMethod = 
              io.grpc.MethodDescriptor.<textservice.ORCRequest, textservice.ORCReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "detectText"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ORCRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ORCReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("detectText"))
                  .build();
          }
        }
     }
     return getDetectTextMethod;
  }

  private static volatile io.grpc.MethodDescriptor<textservice.ORCResultRequest,
      textservice.ORCResultReply> getReceiveTextDetectedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveTextDetected",
      requestType = textservice.ORCResultRequest.class,
      responseType = textservice.ORCResultReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<textservice.ORCResultRequest,
      textservice.ORCResultReply> getReceiveTextDetectedMethod() {
    io.grpc.MethodDescriptor<textservice.ORCResultRequest, textservice.ORCResultReply> getReceiveTextDetectedMethod;
    if ((getReceiveTextDetectedMethod = TextServiceGrpc.getReceiveTextDetectedMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getReceiveTextDetectedMethod = TextServiceGrpc.getReceiveTextDetectedMethod) == null) {
          TextServiceGrpc.getReceiveTextDetectedMethod = getReceiveTextDetectedMethod = 
              io.grpc.MethodDescriptor.<textservice.ORCResultRequest, textservice.ORCResultReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "receiveTextDetected"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ORCResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.ORCResultReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("receiveTextDetected"))
                  .build();
          }
        }
     }
     return getReceiveTextDetectedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<textservice.TranslateResultRequest,
      textservice.TranslateResultReply> getReceiveTextTranslatedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "receiveTextTranslated",
      requestType = textservice.TranslateResultRequest.class,
      responseType = textservice.TranslateResultReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<textservice.TranslateResultRequest,
      textservice.TranslateResultReply> getReceiveTextTranslatedMethod() {
    io.grpc.MethodDescriptor<textservice.TranslateResultRequest, textservice.TranslateResultReply> getReceiveTextTranslatedMethod;
    if ((getReceiveTextTranslatedMethod = TextServiceGrpc.getReceiveTextTranslatedMethod) == null) {
      synchronized (TextServiceGrpc.class) {
        if ((getReceiveTextTranslatedMethod = TextServiceGrpc.getReceiveTextTranslatedMethod) == null) {
          TextServiceGrpc.getReceiveTextTranslatedMethod = getReceiveTextTranslatedMethod = 
              io.grpc.MethodDescriptor.<textservice.TranslateResultRequest, textservice.TranslateResultReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "textservice.TextService", "receiveTextTranslated"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.TranslateResultRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  textservice.TranslateResultReply.getDefaultInstance()))
                  .setSchemaDescriptor(new TextServiceMethodDescriptorSupplier("receiveTextTranslated"))
                  .build();
          }
        }
     }
     return getReceiveTextTranslatedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TextServiceStub newStub(io.grpc.Channel channel) {
    return new TextServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TextServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TextServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TextServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TextServiceFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition
   * </pre>
   */
  public static abstract class TextServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void login(textservice.LoginRequest request,
        io.grpc.stub.StreamObserver<textservice.LoginReply> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     */
    public void logout(textservice.LogOutRequest request,
        io.grpc.stub.StreamObserver<textservice.LogOutReply> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<textservice.ImageRequest> sendImage(
        io.grpc.stub.StreamObserver<textservice.ImageReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendImageMethod(), responseObserver);
    }

    /**
     */
    public void detectText(textservice.ORCRequest request,
        io.grpc.stub.StreamObserver<textservice.ORCReply> responseObserver) {
      asyncUnimplementedUnaryCall(getDetectTextMethod(), responseObserver);
    }

    /**
     */
    public void receiveTextDetected(textservice.ORCResultRequest request,
        io.grpc.stub.StreamObserver<textservice.ORCResultReply> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveTextDetectedMethod(), responseObserver);
    }

    /**
     */
    public void receiveTextTranslated(textservice.TranslateResultRequest request,
        io.grpc.stub.StreamObserver<textservice.TranslateResultReply> responseObserver) {
      asyncUnimplementedUnaryCall(getReceiveTextTranslatedMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                textservice.LoginRequest,
                textservice.LoginReply>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                textservice.LogOutRequest,
                textservice.LogOutReply>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            getSendImageMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                textservice.ImageRequest,
                textservice.ImageReply>(
                  this, METHODID_SEND_IMAGE)))
          .addMethod(
            getDetectTextMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                textservice.ORCRequest,
                textservice.ORCReply>(
                  this, METHODID_DETECT_TEXT)))
          .addMethod(
            getReceiveTextDetectedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                textservice.ORCResultRequest,
                textservice.ORCResultReply>(
                  this, METHODID_RECEIVE_TEXT_DETECTED)))
          .addMethod(
            getReceiveTextTranslatedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                textservice.TranslateResultRequest,
                textservice.TranslateResultReply>(
                  this, METHODID_RECEIVE_TEXT_TRANSLATED)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition
   * </pre>
   */
  public static final class TextServiceStub extends io.grpc.stub.AbstractStub<TextServiceStub> {
    private TextServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TextServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TextServiceStub(channel, callOptions);
    }

    /**
     */
    public void login(textservice.LoginRequest request,
        io.grpc.stub.StreamObserver<textservice.LoginReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void logout(textservice.LogOutRequest request,
        io.grpc.stub.StreamObserver<textservice.LogOutReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<textservice.ImageRequest> sendImage(
        io.grpc.stub.StreamObserver<textservice.ImageReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSendImageMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void detectText(textservice.ORCRequest request,
        io.grpc.stub.StreamObserver<textservice.ORCReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDetectTextMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveTextDetected(textservice.ORCResultRequest request,
        io.grpc.stub.StreamObserver<textservice.ORCResultReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReceiveTextDetectedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void receiveTextTranslated(textservice.TranslateResultRequest request,
        io.grpc.stub.StreamObserver<textservice.TranslateResultReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReceiveTextTranslatedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition
   * </pre>
   */
  public static final class TextServiceBlockingStub extends io.grpc.stub.AbstractStub<TextServiceBlockingStub> {
    private TextServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TextServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TextServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public textservice.LoginReply login(textservice.LoginRequest request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     */
    public textservice.LogOutReply logout(textservice.LogOutRequest request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }

    /**
     */
    public textservice.ORCReply detectText(textservice.ORCRequest request) {
      return blockingUnaryCall(
          getChannel(), getDetectTextMethod(), getCallOptions(), request);
    }

    /**
     */
    public textservice.ORCResultReply receiveTextDetected(textservice.ORCResultRequest request) {
      return blockingUnaryCall(
          getChannel(), getReceiveTextDetectedMethod(), getCallOptions(), request);
    }

    /**
     */
    public textservice.TranslateResultReply receiveTextTranslated(textservice.TranslateResultRequest request) {
      return blockingUnaryCall(
          getChannel(), getReceiveTextTranslatedMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition
   * </pre>
   */
  public static final class TextServiceFutureStub extends io.grpc.stub.AbstractStub<TextServiceFutureStub> {
    private TextServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TextServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TextServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TextServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<textservice.LoginReply> login(
        textservice.LoginRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<textservice.LogOutReply> logout(
        textservice.LogOutRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<textservice.ORCReply> detectText(
        textservice.ORCRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getDetectTextMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<textservice.ORCResultReply> receiveTextDetected(
        textservice.ORCResultRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReceiveTextDetectedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<textservice.TranslateResultReply> receiveTextTranslated(
        textservice.TranslateResultRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getReceiveTextTranslatedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LOGIN = 0;
  private static final int METHODID_LOGOUT = 1;
  private static final int METHODID_DETECT_TEXT = 2;
  private static final int METHODID_RECEIVE_TEXT_DETECTED = 3;
  private static final int METHODID_RECEIVE_TEXT_TRANSLATED = 4;
  private static final int METHODID_SEND_IMAGE = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TextServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TextServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LOGIN:
          serviceImpl.login((textservice.LoginRequest) request,
              (io.grpc.stub.StreamObserver<textservice.LoginReply>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((textservice.LogOutRequest) request,
              (io.grpc.stub.StreamObserver<textservice.LogOutReply>) responseObserver);
          break;
        case METHODID_DETECT_TEXT:
          serviceImpl.detectText((textservice.ORCRequest) request,
              (io.grpc.stub.StreamObserver<textservice.ORCReply>) responseObserver);
          break;
        case METHODID_RECEIVE_TEXT_DETECTED:
          serviceImpl.receiveTextDetected((textservice.ORCResultRequest) request,
              (io.grpc.stub.StreamObserver<textservice.ORCResultReply>) responseObserver);
          break;
        case METHODID_RECEIVE_TEXT_TRANSLATED:
          serviceImpl.receiveTextTranslated((textservice.TranslateResultRequest) request,
              (io.grpc.stub.StreamObserver<textservice.TranslateResultReply>) responseObserver);
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
        case METHODID_SEND_IMAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendImage(
              (io.grpc.stub.StreamObserver<textservice.ImageReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TextServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return textservice.TextServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TextService");
    }
  }

  private static final class TextServiceFileDescriptorSupplier
      extends TextServiceBaseDescriptorSupplier {
    TextServiceFileDescriptorSupplier() {}
  }

  private static final class TextServiceMethodDescriptorSupplier
      extends TextServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TextServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TextServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TextServiceFileDescriptorSupplier())
              .addMethod(getLoginMethod())
              .addMethod(getLogoutMethod())
              .addMethod(getSendImageMethod())
              .addMethod(getDetectTextMethod())
              .addMethod(getReceiveTextDetectedMethod())
              .addMethod(getReceiveTextTranslatedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
