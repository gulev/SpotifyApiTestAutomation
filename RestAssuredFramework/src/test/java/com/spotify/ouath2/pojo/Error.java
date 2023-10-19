package com.spotify.ouath2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Getter
@Setter
@Jacksonized
@Builder
public class Error {

    @JsonProperty("error")
    private InnerError error;

}