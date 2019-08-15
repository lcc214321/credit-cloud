package com.csci.cloud.core.server.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ApiStatisticsStreamChannel {

  public static final String API_RECORD_INPUT_CHANNEL = "api-record-input-channel";
  public static final String API_RECORD_OUTPUT_CHANNEL = "api-record-output-channel";

  @Input(API_RECORD_INPUT_CHANNEL)
  SubscribableChannel apiRecordInput();

  @Output(API_RECORD_OUTPUT_CHANNEL)
  MessageChannel apiRecordOutput();
}
