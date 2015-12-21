package com.interview.framework.norbert;


import com.google.protobuf.InvalidProtocolBufferException;
import com.interview.framework.proto.Search;
import com.linkedin.norbert.network.Serializer;

public class SearchResponseSerializer implements Serializer<Search.Query, Search.Response> {
  public String requestName() {
    return "SearchQuery";
  }

  public String responseName() {
    return "SearchResponse";
  }

  public Search.Query requestFromBytes(byte[] bytes) {
    try {
      return Search.Query.newBuilder().mergeFrom(bytes).build();
    } catch (InvalidProtocolBufferException e) {
      System.out.println("Invalid protocol buffer exception " + e.getMessage());
      throw new IllegalArgumentException(e);
    }
  }

  public Search.Response responseFromBytes(byte[] bytes) {
    try {
      System.out
          .println("Search response Docs list: " + Search.Response.parseFrom(bytes).getDocsList());
      return Search.Response.parseFrom(bytes);
    } catch (InvalidProtocolBufferException e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public byte[] requestToBytes(Search.Query query) {
    return query.toByteArray();
  }

  @Override
  public byte[] responseToBytes(Search.Response response) {
    return response.toByteArray();
  }
}
