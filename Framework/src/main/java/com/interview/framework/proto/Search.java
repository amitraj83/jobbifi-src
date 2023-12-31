// Generated by the protocol buffer compiler. DO NOT EDIT!
// source: src/proto/search.proto

package com.interview.framework.proto;

public final class Search {
  private Search() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {}

  public enum SearchType implements com.google.protobuf.ProtocolMessageEnum {
    INTERVIEW(0, 1), INTERVIEWEE(1, 2), INTERVIEWER(2, 3), JOB(3, 4),;

    public static final int INTERVIEW_VALUE = 1;
    public static final int INTERVIEWEE_VALUE = 2;
    public static final int INTERVIEWER_VALUE = 3;
    public static final int JOB_VALUE = 4;


    public final int getNumber() {
      return value;
    }

    public static SearchType valueOf(int value) {
      switch (value) {
        case 1:
          return INTERVIEW;
        case 2:
          return INTERVIEWEE;
        case 3:
          return INTERVIEWER;
        case 4:
          return JOB;
        default:
          return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<SearchType> internalGetValueMap() {
      return internalValueMap;
    }

    private static com.google.protobuf.Internal.EnumLiteMap<SearchType> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<SearchType>() {
          public SearchType findValueByNumber(int number) {
            return SearchType.valueOf(number);
          }
        };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor getValueDescriptor() {
      return getDescriptor().getValues().get(index);
    }

    public final com.google.protobuf.Descriptors.EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }

    public static final com.google.protobuf.Descriptors.EnumDescriptor getDescriptor() {
      return com.interview.framework.proto.Search.getDescriptor().getEnumTypes().get(0);
    }

    private static final SearchType[] VALUES = {INTERVIEW, INTERVIEWEE, INTERVIEWER, JOB,};

    public static SearchType valueOf(com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException("EnumValueDescriptor is not for this type.");
      }
      return VALUES[desc.getIndex()];
    }

    private final int index;
    private final int value;

    private SearchType(int index, int value) {
      this.index = index;
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:proto.SearchType)
  }

  public interface QueryOrBuilder extends com.google.protobuf.MessageOrBuilder {

    // required string value = 1;
    boolean hasValue();

    String getValue();

    // required .proto.SearchType type = 2;
    boolean hasType();

    com.interview.framework.proto.Search.SearchType getType();
  }
  public static final class Query extends com.google.protobuf.GeneratedMessage
      implements QueryOrBuilder {
    // Use Query.newBuilder() to construct.
    private Query(Builder builder) {
      super(builder);
    }

    private Query(boolean noInit) {}

    private static final Query defaultInstance;

    public static Query getDefaultInstance() {
      return defaultInstance;
    }

    public Query getDefaultInstanceForType() {
      return defaultInstance;
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.interview.framework.proto.Search.internal_static_proto_Query_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return com.interview.framework.proto.Search.internal_static_proto_Query_fieldAccessorTable;
    }

    private int bitField0_;
    // required string value = 1;
    public static final int VALUE_FIELD_NUMBER = 1;
    private java.lang.Object value_;

    public boolean hasValue() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }

    public String getValue() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          value_ = s;
        }
        return s;
      }
    }

    private com.google.protobuf.ByteString getValueBytes() {
      java.lang.Object ref = value_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        value_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    // required .proto.SearchType type = 2;
    public static final int TYPE_FIELD_NUMBER = 2;
    private com.interview.framework.proto.Search.SearchType type_;

    public boolean hasType() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }

    public com.interview.framework.proto.Search.SearchType getType() {
      return type_;
    }

    private void initFields() {
      value_ = "";
      type_ = com.interview.framework.proto.Search.SearchType.INTERVIEW;
    }

    private byte memoizedIsInitialized = -1;

    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1)
        return isInitialized == 1;

      if (!hasValue()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasType()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeBytes(1, getValueBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeEnum(2, type_.getNumber());
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1)
        return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream.computeBytesSize(1, getValueBytes());
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream.computeEnumSize(2, type_.getNumber());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;

    @java.lang.Override
    protected java.lang.Object writeReplace() throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(
        com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseDelimitedFrom(
        java.io.InputStream input) throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }

    public static com.interview.framework.proto.Search.Query parseDelimitedFrom(
        java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }

    public static com.interview.framework.proto.Search.Query parseFrom(
        com.google.protobuf.CodedInputStream input) throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }

    public static com.interview.framework.proto.Search.Query parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
    }

    public static Builder newBuilder() {
      return Builder.create();
    }

    public Builder newBuilderForType() {
      return newBuilder();
    }

    public static Builder newBuilder(com.interview.framework.proto.Search.Query prototype) {
      return newBuilder().mergeFrom(prototype);
    }

    public Builder toBuilder() {
      return newBuilder(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
        implements com.interview.framework.proto.Search.QueryOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return com.interview.framework.proto.Search.internal_static_proto_Query_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return com.interview.framework.proto.Search.internal_static_proto_Query_fieldAccessorTable;
      }

      // Construct using com.interview.framework.proto.Search.Query.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }

      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        value_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        type_ = com.interview.framework.proto.Search.SearchType.INTERVIEW;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
        return com.interview.framework.proto.Search.Query.getDescriptor();
      }

      public com.interview.framework.proto.Search.Query getDefaultInstanceForType() {
        return com.interview.framework.proto.Search.Query.getDefaultInstance();
      }

      public com.interview.framework.proto.Search.Query build() {
        com.interview.framework.proto.Search.Query result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      private com.interview.framework.proto.Search.Query buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        com.interview.framework.proto.Search.Query result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
        }
        return result;
      }

      public com.interview.framework.proto.Search.Query buildPartial() {
        com.interview.framework.proto.Search.Query result =
            new com.interview.framework.proto.Search.Query(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.value_ = value_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.type_ = type_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.interview.framework.proto.Search.Query) {
          return mergeFrom((com.interview.framework.proto.Search.Query) other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.interview.framework.proto.Search.Query other) {
        if (other == com.interview.framework.proto.Search.Query.getDefaultInstance())
          return this;
        if (other.hasValue()) {
          setValue(other.getValue());
        }
        if (other.hasType()) {
          setType(other.getType());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasValue()) {

          return false;
        }
        if (!hasType()) {

          return false;
        }
        return true;
      }

      public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder(this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              bitField0_ |= 0x00000001;
              value_ = input.readBytes();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();
              com.interview.framework.proto.Search.SearchType value =
                  com.interview.framework.proto.Search.SearchType.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(2, rawValue);
              } else {
                bitField0_ |= 0x00000002;
                type_ = value;
              }
              break;
            }
          }
        }
      }

      private int bitField0_;

      // required string value = 1;
      private java.lang.Object value_ = "";

      public boolean hasValue() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }

      public String getValue() {
        java.lang.Object ref = value_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          value_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }

      public Builder setValue(String value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000001;
        value_ = value;
        onChanged();
        return this;
      }

      public Builder clearValue() {
        bitField0_ = (bitField0_ & ~0x00000001);
        value_ = getDefaultInstance().getValue();
        onChanged();
        return this;
      }

      void setValue(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000001;
        value_ = value;
        onChanged();
      }

      // required .proto.SearchType type = 2;
      private com.interview.framework.proto.Search.SearchType type_ =
          com.interview.framework.proto.Search.SearchType.INTERVIEW;

      public boolean hasType() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }

      public com.interview.framework.proto.Search.SearchType getType() {
        return type_;
      }

      public Builder setType(com.interview.framework.proto.Search.SearchType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        type_ = value;
        onChanged();
        return this;
      }

      public Builder clearType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        type_ = com.interview.framework.proto.Search.SearchType.INTERVIEW;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:proto.Query)
    }

    static {
      defaultInstance = new Query(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.Query)
  }

  public interface ResponseOrBuilder extends com.google.protobuf.MessageOrBuilder {

    // repeated string docs = 1;
    java.util.List<String> getDocsList();

    int getDocsCount();

    String getDocs(int index);
  }
  public static final class Response extends com.google.protobuf.GeneratedMessage
      implements ResponseOrBuilder {
    // Use Response.newBuilder() to construct.
    private Response(Builder builder) {
      super(builder);
    }

    private Response(boolean noInit) {}

    private static final Response defaultInstance;

    public static Response getDefaultInstance() {
      return defaultInstance;
    }

    public Response getDefaultInstanceForType() {
      return defaultInstance;
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.interview.framework.proto.Search.internal_static_proto_Response_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
      return com.interview.framework.proto.Search.internal_static_proto_Response_fieldAccessorTable;
    }

    // repeated string docs = 1;
    public static final int DOCS_FIELD_NUMBER = 1;
    private com.google.protobuf.LazyStringList docs_;

    public java.util.List<String> getDocsList() {
      return docs_;
    }

    public int getDocsCount() {
      return docs_.size();
    }

    public String getDocs(int index) {
      return docs_.get(index);
    }

    private void initFields() {
      docs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    private byte memoizedIsInitialized = -1;

    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1)
        return isInitialized == 1;

      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
      getSerializedSize();
      for (int i = 0; i < docs_.size(); i++) {
        output.writeBytes(1, docs_.getByteString(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;

    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1)
        return size;

      size = 0;
      {
        int dataSize = 0;
        for (int i = 0; i < docs_.size(); i++) {
          dataSize +=
              com.google.protobuf.CodedOutputStream.computeBytesSizeNoTag(docs_.getByteString(i));
        }
        size += dataSize;
        size += 1 * getDocsList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;

    @java.lang.Override
    protected java.lang.Object writeReplace() throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(
        com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseDelimitedFrom(
        java.io.InputStream input) throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }

    public static com.interview.framework.proto.Search.Response parseDelimitedFrom(
        java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }

    public static com.interview.framework.proto.Search.Response parseFrom(
        com.google.protobuf.CodedInputStream input) throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }

    public static com.interview.framework.proto.Search.Response parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry).buildParsed();
    }

    public static Builder newBuilder() {
      return Builder.create();
    }

    public Builder newBuilderForType() {
      return newBuilder();
    }

    public static Builder newBuilder(com.interview.framework.proto.Search.Response prototype) {
      return newBuilder().mergeFrom(prototype);
    }

    public Builder toBuilder() {
      return newBuilder(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }

    public static final class Builder extends com.google.protobuf.GeneratedMessage.Builder<Builder>
        implements com.interview.framework.proto.Search.ResponseOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return com.interview.framework.proto.Search.internal_static_proto_Response_descriptor;
      }

      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return com.interview.framework.proto.Search.internal_static_proto_Response_fieldAccessorTable;
      }

      // Construct using com.interview.framework.proto.Search.Response.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }

      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }

      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        docs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
        return com.interview.framework.proto.Search.Response.getDescriptor();
      }

      public com.interview.framework.proto.Search.Response getDefaultInstanceForType() {
        return com.interview.framework.proto.Search.Response.getDefaultInstance();
      }

      public com.interview.framework.proto.Search.Response build() {
        com.interview.framework.proto.Search.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      private com.interview.framework.proto.Search.Response buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        com.interview.framework.proto.Search.Response result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result).asInvalidProtocolBufferException();
        }
        return result;
      }

      public com.interview.framework.proto.Search.Response buildPartial() {
        com.interview.framework.proto.Search.Response result =
            new com.interview.framework.proto.Search.Response(this);
        int from_bitField0_ = bitField0_;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
          docs_ = new com.google.protobuf.UnmodifiableLazyStringList(docs_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.docs_ = docs_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.interview.framework.proto.Search.Response) {
          return mergeFrom((com.interview.framework.proto.Search.Response) other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.interview.framework.proto.Search.Response other) {
        if (other == com.interview.framework.proto.Search.Response.getDefaultInstance())
          return this;
        if (!other.docs_.isEmpty()) {
          if (docs_.isEmpty()) {
            docs_ = other.docs_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureDocsIsMutable();
            docs_.addAll(other.docs_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        return true;
      }

      public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
            com.google.protobuf.UnknownFieldSet.newBuilder(this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              onChanged();
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                onChanged();
                return this;
              }
              break;
            }
            case 10: {
              ensureDocsIsMutable();
              docs_.add(input.readBytes());
              break;
            }
          }
        }
      }

      private int bitField0_;

      // repeated string docs = 1;
      private com.google.protobuf.LazyStringList docs_ =
          com.google.protobuf.LazyStringArrayList.EMPTY;

      private void ensureDocsIsMutable() {
        if (!((bitField0_ & 0x00000001) == 0x00000001)) {
          docs_ = new com.google.protobuf.LazyStringArrayList(docs_);
          bitField0_ |= 0x00000001;
        }
      }

      public java.util.List<String> getDocsList() {
        return java.util.Collections.unmodifiableList(docs_);
      }

      public int getDocsCount() {
        return docs_.size();
      }

      public String getDocs(int index) {
        return docs_.get(index);
      }

      public Builder setDocs(int index, String value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDocsIsMutable();
        docs_.set(index, value);
        onChanged();
        return this;
      }

      public Builder addDocs(String value) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDocsIsMutable();
        docs_.add(value);
        onChanged();
        return this;
      }

      public Builder addAllDocs(java.lang.Iterable<String> values) {
        ensureDocsIsMutable();
        super.addAll(values, docs_);
        onChanged();
        return this;
      }

      public Builder clearDocs() {
        docs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
        return this;
      }

      void addDocs(com.google.protobuf.ByteString value) {
        ensureDocsIsMutable();
        docs_.add(value);
        onChanged();
      }

      // @@protoc_insertion_point(builder_scope:proto.Response)
    }

    static {
      defaultInstance = new Response(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:proto.Response)
  }

  private static com.google.protobuf.Descriptors.Descriptor internal_static_proto_Query_descriptor;
  private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_proto_Query_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor internal_static_proto_Response_descriptor;
  private static com.google.protobuf.GeneratedMessage.FieldAccessorTable internal_static_proto_Response_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    java.lang.String[] descriptorData = {"\n\026src/proto/search.proto\022\005proto\"7\n\005Query"
        + "\022\r\n\005value\030\001 \002(\t\022\037\n\004type\030\002 \002(\0162\021.proto.Se"
        + "archType\"\030\n\010Response\022\014\n\004docs\030\001 \003(\t*F\n\nSe"
        + "archType\022\r\n\tINTERVIEW\020\001\022\017\n\013INTERVIEWEE\020\002"
        + "\022\017\n\013INTERVIEWER\020\003\022\007\n\003JOB\020\004B\'\n\035com.interv"
        + "iew.framework.protoB\006Search"};
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            internal_static_proto_Query_descriptor = getDescriptor().getMessageTypes().get(0);
            internal_static_proto_Query_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                    internal_static_proto_Query_descriptor,
                    new java.lang.String[] {"Value", "Type",},
                    com.interview.framework.proto.Search.Query.class,
                    com.interview.framework.proto.Search.Query.Builder.class);
            internal_static_proto_Response_descriptor = getDescriptor().getMessageTypes().get(1);
            internal_static_proto_Response_fieldAccessorTable =
                new com.google.protobuf.GeneratedMessage.FieldAccessorTable(
                    internal_static_proto_Response_descriptor, new java.lang.String[] {"Docs",},
                    com.interview.framework.proto.Search.Response.class,
                    com.interview.framework.proto.Search.Response.Builder.class);
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {}, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}
