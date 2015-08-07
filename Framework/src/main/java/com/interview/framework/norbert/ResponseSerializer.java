package com.interview.framework.norbert;

import com.google.protobuf.InvalidProtocolBufferException;
import com.interview.framework.proto.Backend.Request;
import com.interview.framework.proto.Backend.Response;
import com.linkedin.norbert.network.Serializer;

public class ResponseSerializer implements Serializer<Request, Response> {

  @Override
  public Request requestFromBytes(byte[] arg0) {
    try {
      return Request.parseFrom(arg0);
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }
    return Request.newBuilder().build();
  }

  @Override
  public String requestName() {
    return "BackendRequest";
  }

  @Override
  public Response responseFromBytes(byte[] arg0) {
    try {
      return Response.parseFrom(arg0);
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }
    return Response.newBuilder().build();
  }

  @Override
  public byte[] requestToBytes(Request arg0) {
    return arg0.toByteArray();
  }

  @Override
  public String responseName() {
    return "BackendResponse";
  }

  @Override
  public byte[] responseToBytes(Response arg0) {
    return arg0.toByteArray();
  }

}
