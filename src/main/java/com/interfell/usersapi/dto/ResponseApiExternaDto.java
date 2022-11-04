package com.interfell.usersapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author vbocanegra
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApiExternaDto {

    /**
     * atributo responseCode.
     */
    private long responseCode;

    /**
     * atributo description.
     */
    private String description;

    /**
     * atributo elapsedTime.
     */
    private long elapsedTime;

    /**
     * atributo result.
     */
    private Result result;

    /**
     * Get the value of responseCode
     *
     * @return the value of responseCode
     */
    public long getResponseCode() {
        return responseCode;
    }

    /**
     * Set the value of responseCode
     *
     * @param responseCode new value of responseCode
     */
    public void setResponseCode(long responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of elapsedTime
     *
     * @return the value of elapsedTime
     */
    public long getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Set the value of elapsedTime
     *
     * @param elapsedTime new value of elapsedTime
     */
    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Get the value of result
     *
     * @return the value of result
     */
    public Result getResult() {
        return result;
    }

    /**
     * Set the value of result
     *
     * @param result new value of result
     */
    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        /**
         * atributo registerCount.
         */
        private int registerCount;

        
        public Result(int registerCount) {
            this.registerCount = registerCount;
        }
        
        /**
         * Get the value of registerCount
         *
         * @return the value of registerCount
         */
        public int getRegisterCount() {
            return registerCount;
        }

        /**
         * Set the value of registerCount
         *
         * @param registerCount new value of registerCount
         */
        public void setRegisterCount(int registerCount) {
            this.registerCount = registerCount;
        }

    }

}
