package com.cmm.dto.servicesdto;

import com.cmm.entities.ServiceMaster;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequestDetailsDTO {
    private Long serviceName;
    private String serviceRequestTitle;
    private List<ServiceDto> selectedServices;
    private List<ServiceImg> serviceDescImages;
    private String serviceDescription;
    private String isPickupAndDropRequested;
    private Date pickupDateAndTime;

    public Optional<Timestamp> getPickupDateAndTime() {
        if (this.pickupDateAndTime == null) {
            return Optional.empty();
        }

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        inputDateFormat.setTimeZone(TimeZone.getTimeZone("IST"));
        Date parsedDate;

        try {
            parsedDate = inputDateFormat.parse(this.pickupDateAndTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDateString = outputDateFormat.format(parsedDate);

        try {
            Date formattedDate = outputDateFormat.parse(formattedDateString);
            return Optional.of(new Timestamp(formattedDate.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
