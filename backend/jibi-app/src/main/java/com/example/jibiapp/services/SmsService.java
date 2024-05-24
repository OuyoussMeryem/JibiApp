package com.example.jibiapp.services;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final VonageClient vonageClient;

    public SmsService(@Value("${vonage.api.key}") String apiKey,
                      @Value("${vonage.api.secret}") String apiSecret) {
        this.vonageClient = VonageClient.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }

    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("0")) {
            return "+212" + phoneNumber.substring(1);
        }
        return phoneNumber;
    }

    public void sendSms(String toPhoneNumber, String messageContent) {
        toPhoneNumber = formatPhoneNumber(toPhoneNumber);
        TextMessage message = new TextMessage("VonageSMS", toPhoneNumber, messageContent);

        SmsSubmissionResponse response = vonageClient.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}
