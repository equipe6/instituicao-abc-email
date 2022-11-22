package br.edu.unisinos.instituicaoabc.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

public abstract class BaseDto implements Serializable {

    private static final long serialVersionUID = 4279254907817888530L;

    @Override
    public String toString() {
        return this.toString(false);
    }

    public String toString(boolean prettyPrinter) {
        try {
            if (prettyPrinter) {
                return new ObjectMapper() //
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL) //
                        .writerWithDefaultPrettyPrinter() //
                        .writeValueAsString(this);
            } else {
                return new ObjectMapper() //
                        .setSerializationInclusion(JsonInclude.Include.NON_NULL) //
                        .writeValueAsString(this);
            }
        } catch (JsonProcessingException e) {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public String toStringOriginal() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }

}
