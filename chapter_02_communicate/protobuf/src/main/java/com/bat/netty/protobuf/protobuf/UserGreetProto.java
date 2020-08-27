// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserGreet.proto

package com.bat.netty.protobuf.protobuf;

public final class UserGreetProto {
  private UserGreetProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserGreetOrBuilder extends
      // @@protoc_insertion_point(interface_extends:netty.UserGreet)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string username = 1;</code>
     * @return Whether the username field is set.
     */
    boolean hasUsername();
    /**
     * <code>required string username = 1;</code>
     * @return The username.
     */
    String getUsername();
    /**
     * <code>required string username = 1;</code>
     * @return The bytes for username.
     */
    com.google.protobuf.ByteString
        getUsernameBytes();

    /**
     * <code>required string greet = 2;</code>
     * @return Whether the greet field is set.
     */
    boolean hasGreet();
    /**
     * <code>required string greet = 2;</code>
     * @return The greet.
     */
    String getGreet();
    /**
     * <code>required string greet = 2;</code>
     * @return The bytes for greet.
     */
    com.google.protobuf.ByteString
        getGreetBytes();
  }
  /**
   * Protobuf type {@code netty.UserGreet}
   */
  public static final class UserGreet extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:netty.UserGreet)
      UserGreetOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use UserGreet.newBuilder() to construct.
    private UserGreet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private UserGreet() {
      username_ = "";
      greet_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new UserGreet();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private UserGreet(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
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
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              username_ = bs;
              break;
            }
            case 18: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000002;
              greet_ = bs;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
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
      return UserGreetProto.internal_static_netty_UserGreet_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return UserGreetProto.internal_static_netty_UserGreet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserGreet.class, Builder.class);
    }

    private int bitField0_;
    public static final int USERNAME_FIELD_NUMBER = 1;
    private volatile Object username_;
    /**
     * <code>required string username = 1;</code>
     * @return Whether the username field is set.
     */
    @Override
    public boolean hasUsername() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required string username = 1;</code>
     * @return The username.
     */
    @Override
    public String getUsername() {
      Object ref = username_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          username_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string username = 1;</code>
     * @return The bytes for username.
     */
    @Override
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      Object ref = username_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int GREET_FIELD_NUMBER = 2;
    private volatile Object greet_;
    /**
     * <code>required string greet = 2;</code>
     * @return Whether the greet field is set.
     */
    @Override
    public boolean hasGreet() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required string greet = 2;</code>
     * @return The greet.
     */
    @Override
    public String getGreet() {
      Object ref = greet_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          greet_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string greet = 2;</code>
     * @return The bytes for greet.
     */
    @Override
    public com.google.protobuf.ByteString
        getGreetBytes() {
      Object ref = greet_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        greet_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasUsername()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasGreet()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, username_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, greet_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, username_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, greet_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof UserGreet)) {
        return super.equals(obj);
      }
      UserGreet other = (UserGreet) obj;

      if (hasUsername() != other.hasUsername()) return false;
      if (hasUsername()) {
        if (!getUsername()
            .equals(other.getUsername())) return false;
      }
      if (hasGreet() != other.hasGreet()) return false;
      if (hasGreet()) {
        if (!getGreet()
            .equals(other.getGreet())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasUsername()) {
        hash = (37 * hash) + USERNAME_FIELD_NUMBER;
        hash = (53 * hash) + getUsername().hashCode();
      }
      if (hasGreet()) {
        hash = (37 * hash) + GREET_FIELD_NUMBER;
        hash = (53 * hash) + getGreet().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static UserGreet parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserGreet parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserGreet parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserGreet parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserGreet parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserGreet parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserGreet parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static UserGreet parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static UserGreet parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static UserGreet parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static UserGreet parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static UserGreet parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(UserGreet prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code netty.UserGreet}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:netty.UserGreet)
        UserGreetOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return UserGreetProto.internal_static_netty_UserGreet_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return UserGreetProto.internal_static_netty_UserGreet_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                UserGreet.class, Builder.class);
      }

      // Construct using com.bat.netty.protobuf.protobuf.UserGreetProto.UserGreet.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        username_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        greet_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return UserGreetProto.internal_static_netty_UserGreet_descriptor;
      }

      @Override
      public UserGreet getDefaultInstanceForType() {
        return UserGreet.getDefaultInstance();
      }

      @Override
      public UserGreet build() {
        UserGreet result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public UserGreet buildPartial() {
        UserGreet result = new UserGreet(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          to_bitField0_ |= 0x00000001;
        }
        result.username_ = username_;
        if (((from_bitField0_ & 0x00000002) != 0)) {
          to_bitField0_ |= 0x00000002;
        }
        result.greet_ = greet_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof UserGreet) {
          return mergeFrom((UserGreet)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(UserGreet other) {
        if (other == UserGreet.getDefaultInstance()) return this;
        if (other.hasUsername()) {
          bitField0_ |= 0x00000001;
          username_ = other.username_;
          onChanged();
        }
        if (other.hasGreet()) {
          bitField0_ |= 0x00000002;
          greet_ = other.greet_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        if (!hasUsername()) {
          return false;
        }
        if (!hasGreet()) {
          return false;
        }
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        UserGreet parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (UserGreet) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private Object username_ = "";
      /**
       * <code>required string username = 1;</code>
       * @return Whether the username field is set.
       */
      public boolean hasUsername() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>required string username = 1;</code>
       * @return The username.
       */
      public String getUsername() {
        Object ref = username_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            username_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string username = 1;</code>
       * @return The bytes for username.
       */
      public com.google.protobuf.ByteString
          getUsernameBytes() {
        Object ref = username_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          username_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string username = 1;</code>
       * @param value The username to set.
       * @return This builder for chaining.
       */
      public Builder setUsername(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        username_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string username = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearUsername() {
        bitField0_ = (bitField0_ & ~0x00000001);
        username_ = getDefaultInstance().getUsername();
        onChanged();
        return this;
      }
      /**
       * <code>required string username = 1;</code>
       * @param value The bytes for username to set.
       * @return This builder for chaining.
       */
      public Builder setUsernameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        username_ = value;
        onChanged();
        return this;
      }

      private Object greet_ = "";
      /**
       * <code>required string greet = 2;</code>
       * @return Whether the greet field is set.
       */
      public boolean hasGreet() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>required string greet = 2;</code>
       * @return The greet.
       */
      public String getGreet() {
        Object ref = greet_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            greet_ = s;
          }
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>required string greet = 2;</code>
       * @return The bytes for greet.
       */
      public com.google.protobuf.ByteString
          getGreetBytes() {
        Object ref = greet_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          greet_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string greet = 2;</code>
       * @param value The greet to set.
       * @return This builder for chaining.
       */
      public Builder setGreet(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        greet_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string greet = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearGreet() {
        bitField0_ = (bitField0_ & ~0x00000002);
        greet_ = getDefaultInstance().getGreet();
        onChanged();
        return this;
      }
      /**
       * <code>required string greet = 2;</code>
       * @param value The bytes for greet to set.
       * @return This builder for chaining.
       */
      public Builder setGreetBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        greet_ = value;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:netty.UserGreet)
    }

    // @@protoc_insertion_point(class_scope:netty.UserGreet)
    private static final UserGreet DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new UserGreet();
    }

    public static UserGreet getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @Deprecated public static final com.google.protobuf.Parser<UserGreet>
        PARSER = new com.google.protobuf.AbstractParser<UserGreet>() {
      @Override
      public UserGreet parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UserGreet(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<UserGreet> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<UserGreet> getParserForType() {
      return PARSER;
    }

    @Override
    public UserGreet getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_netty_UserGreet_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_netty_UserGreet_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\022CustomStruct.proto\022\005netty\",\n\tUserGreet" +
      "\022\020\n\010username\030\001 \002(\t\022\r\n\005greet\030\002 \002(\tB1\n\037com" +
      ".bat.netty.protobuf.protobufB\016UserGreetP" +
      "roto"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_netty_UserGreet_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_netty_UserGreet_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_netty_UserGreet_descriptor,
        new String[] { "Username", "Greet", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
