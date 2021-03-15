// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TextService.proto

package textservice;

/**
 * Protobuf type {@code textservice.ImageRequest}
 */
public  final class ImageRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:textservice.ImageRequest)
    ImageRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ImageRequest.newBuilder() to construct.
  private ImageRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ImageRequest() {
    sessionId_ = 0;
    imageId_ = 0;
    bytesSent_ = com.google.protobuf.ByteString.EMPTY;
    maxSize_ = 0;
    translationId_ = "";
    contentType_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ImageRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            sessionId_ = input.readInt32();
            break;
          }
          case 16: {

            imageId_ = input.readInt32();
            break;
          }
          case 26: {

            bytesSent_ = input.readBytes();
            break;
          }
          case 32: {

            maxSize_ = input.readInt32();
            break;
          }
          case 42: {
            java.lang.String s = input.readStringRequireUtf8();

            translationId_ = s;
            break;
          }
          case 50: {
            java.lang.String s = input.readStringRequireUtf8();

            contentType_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return textservice.TextServiceOuterClass.internal_static_textservice_ImageRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return textservice.TextServiceOuterClass.internal_static_textservice_ImageRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            textservice.ImageRequest.class, textservice.ImageRequest.Builder.class);
  }

  public static final int SESSIONID_FIELD_NUMBER = 1;
  private int sessionId_;
  /**
   * <code>int32 sessionId = 1;</code>
   */
  public int getSessionId() {
    return sessionId_;
  }

  public static final int IMAGEID_FIELD_NUMBER = 2;
  private int imageId_;
  /**
   * <code>int32 imageId = 2;</code>
   */
  public int getImageId() {
    return imageId_;
  }

  public static final int BYTESSENT_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString bytesSent_;
  /**
   * <code>bytes bytesSent = 3;</code>
   */
  public com.google.protobuf.ByteString getBytesSent() {
    return bytesSent_;
  }

  public static final int MAXSIZE_FIELD_NUMBER = 4;
  private int maxSize_;
  /**
   * <code>int32 maxSize = 4;</code>
   */
  public int getMaxSize() {
    return maxSize_;
  }

  public static final int TRANSLATIONID_FIELD_NUMBER = 5;
  private volatile java.lang.Object translationId_;
  /**
   * <code>string translationId = 5;</code>
   */
  public java.lang.String getTranslationId() {
    java.lang.Object ref = translationId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      translationId_ = s;
      return s;
    }
  }
  /**
   * <code>string translationId = 5;</code>
   */
  public com.google.protobuf.ByteString
      getTranslationIdBytes() {
    java.lang.Object ref = translationId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      translationId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONTENTTYPE_FIELD_NUMBER = 6;
  private volatile java.lang.Object contentType_;
  /**
   * <code>string contentType = 6;</code>
   */
  public java.lang.String getContentType() {
    java.lang.Object ref = contentType_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      contentType_ = s;
      return s;
    }
  }
  /**
   * <code>string contentType = 6;</code>
   */
  public com.google.protobuf.ByteString
      getContentTypeBytes() {
    java.lang.Object ref = contentType_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      contentType_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (sessionId_ != 0) {
      output.writeInt32(1, sessionId_);
    }
    if (imageId_ != 0) {
      output.writeInt32(2, imageId_);
    }
    if (!bytesSent_.isEmpty()) {
      output.writeBytes(3, bytesSent_);
    }
    if (maxSize_ != 0) {
      output.writeInt32(4, maxSize_);
    }
    if (!getTranslationIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, translationId_);
    }
    if (!getContentTypeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, contentType_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (sessionId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, sessionId_);
    }
    if (imageId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, imageId_);
    }
    if (!bytesSent_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, bytesSent_);
    }
    if (maxSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, maxSize_);
    }
    if (!getTranslationIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, translationId_);
    }
    if (!getContentTypeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, contentType_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof textservice.ImageRequest)) {
      return super.equals(obj);
    }
    textservice.ImageRequest other = (textservice.ImageRequest) obj;

    boolean result = true;
    result = result && (getSessionId()
        == other.getSessionId());
    result = result && (getImageId()
        == other.getImageId());
    result = result && getBytesSent()
        .equals(other.getBytesSent());
    result = result && (getMaxSize()
        == other.getMaxSize());
    result = result && getTranslationId()
        .equals(other.getTranslationId());
    result = result && getContentType()
        .equals(other.getContentType());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SESSIONID_FIELD_NUMBER;
    hash = (53 * hash) + getSessionId();
    hash = (37 * hash) + IMAGEID_FIELD_NUMBER;
    hash = (53 * hash) + getImageId();
    hash = (37 * hash) + BYTESSENT_FIELD_NUMBER;
    hash = (53 * hash) + getBytesSent().hashCode();
    hash = (37 * hash) + MAXSIZE_FIELD_NUMBER;
    hash = (53 * hash) + getMaxSize();
    hash = (37 * hash) + TRANSLATIONID_FIELD_NUMBER;
    hash = (53 * hash) + getTranslationId().hashCode();
    hash = (37 * hash) + CONTENTTYPE_FIELD_NUMBER;
    hash = (53 * hash) + getContentType().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static textservice.ImageRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static textservice.ImageRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static textservice.ImageRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static textservice.ImageRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static textservice.ImageRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static textservice.ImageRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static textservice.ImageRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static textservice.ImageRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static textservice.ImageRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static textservice.ImageRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static textservice.ImageRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static textservice.ImageRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(textservice.ImageRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code textservice.ImageRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:textservice.ImageRequest)
      textservice.ImageRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return textservice.TextServiceOuterClass.internal_static_textservice_ImageRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return textservice.TextServiceOuterClass.internal_static_textservice_ImageRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              textservice.ImageRequest.class, textservice.ImageRequest.Builder.class);
    }

    // Construct using textservice.ImageRequest.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      sessionId_ = 0;

      imageId_ = 0;

      bytesSent_ = com.google.protobuf.ByteString.EMPTY;

      maxSize_ = 0;

      translationId_ = "";

      contentType_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return textservice.TextServiceOuterClass.internal_static_textservice_ImageRequest_descriptor;
    }

    public textservice.ImageRequest getDefaultInstanceForType() {
      return textservice.ImageRequest.getDefaultInstance();
    }

    public textservice.ImageRequest build() {
      textservice.ImageRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public textservice.ImageRequest buildPartial() {
      textservice.ImageRequest result = new textservice.ImageRequest(this);
      result.sessionId_ = sessionId_;
      result.imageId_ = imageId_;
      result.bytesSent_ = bytesSent_;
      result.maxSize_ = maxSize_;
      result.translationId_ = translationId_;
      result.contentType_ = contentType_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof textservice.ImageRequest) {
        return mergeFrom((textservice.ImageRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(textservice.ImageRequest other) {
      if (other == textservice.ImageRequest.getDefaultInstance()) return this;
      if (other.getSessionId() != 0) {
        setSessionId(other.getSessionId());
      }
      if (other.getImageId() != 0) {
        setImageId(other.getImageId());
      }
      if (other.getBytesSent() != com.google.protobuf.ByteString.EMPTY) {
        setBytesSent(other.getBytesSent());
      }
      if (other.getMaxSize() != 0) {
        setMaxSize(other.getMaxSize());
      }
      if (!other.getTranslationId().isEmpty()) {
        translationId_ = other.translationId_;
        onChanged();
      }
      if (!other.getContentType().isEmpty()) {
        contentType_ = other.contentType_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      textservice.ImageRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (textservice.ImageRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int sessionId_ ;
    /**
     * <code>int32 sessionId = 1;</code>
     */
    public int getSessionId() {
      return sessionId_;
    }
    /**
     * <code>int32 sessionId = 1;</code>
     */
    public Builder setSessionId(int value) {
      
      sessionId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 sessionId = 1;</code>
     */
    public Builder clearSessionId() {
      
      sessionId_ = 0;
      onChanged();
      return this;
    }

    private int imageId_ ;
    /**
     * <code>int32 imageId = 2;</code>
     */
    public int getImageId() {
      return imageId_;
    }
    /**
     * <code>int32 imageId = 2;</code>
     */
    public Builder setImageId(int value) {
      
      imageId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 imageId = 2;</code>
     */
    public Builder clearImageId() {
      
      imageId_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString bytesSent_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes bytesSent = 3;</code>
     */
    public com.google.protobuf.ByteString getBytesSent() {
      return bytesSent_;
    }
    /**
     * <code>bytes bytesSent = 3;</code>
     */
    public Builder setBytesSent(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      bytesSent_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes bytesSent = 3;</code>
     */
    public Builder clearBytesSent() {
      
      bytesSent_ = getDefaultInstance().getBytesSent();
      onChanged();
      return this;
    }

    private int maxSize_ ;
    /**
     * <code>int32 maxSize = 4;</code>
     */
    public int getMaxSize() {
      return maxSize_;
    }
    /**
     * <code>int32 maxSize = 4;</code>
     */
    public Builder setMaxSize(int value) {
      
      maxSize_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 maxSize = 4;</code>
     */
    public Builder clearMaxSize() {
      
      maxSize_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object translationId_ = "";
    /**
     * <code>string translationId = 5;</code>
     */
    public java.lang.String getTranslationId() {
      java.lang.Object ref = translationId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        translationId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string translationId = 5;</code>
     */
    public com.google.protobuf.ByteString
        getTranslationIdBytes() {
      java.lang.Object ref = translationId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        translationId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string translationId = 5;</code>
     */
    public Builder setTranslationId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      translationId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string translationId = 5;</code>
     */
    public Builder clearTranslationId() {
      
      translationId_ = getDefaultInstance().getTranslationId();
      onChanged();
      return this;
    }
    /**
     * <code>string translationId = 5;</code>
     */
    public Builder setTranslationIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      translationId_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object contentType_ = "";
    /**
     * <code>string contentType = 6;</code>
     */
    public java.lang.String getContentType() {
      java.lang.Object ref = contentType_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        contentType_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string contentType = 6;</code>
     */
    public com.google.protobuf.ByteString
        getContentTypeBytes() {
      java.lang.Object ref = contentType_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        contentType_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string contentType = 6;</code>
     */
    public Builder setContentType(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      contentType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string contentType = 6;</code>
     */
    public Builder clearContentType() {
      
      contentType_ = getDefaultInstance().getContentType();
      onChanged();
      return this;
    }
    /**
     * <code>string contentType = 6;</code>
     */
    public Builder setContentTypeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      contentType_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:textservice.ImageRequest)
  }

  // @@protoc_insertion_point(class_scope:textservice.ImageRequest)
  private static final textservice.ImageRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new textservice.ImageRequest();
  }

  public static textservice.ImageRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ImageRequest>
      PARSER = new com.google.protobuf.AbstractParser<ImageRequest>() {
    public ImageRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ImageRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ImageRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ImageRequest> getParserForType() {
    return PARSER;
  }

  public textservice.ImageRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
