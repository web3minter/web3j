/*
 * Copyright 2025 Web3 Labs Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.web3j.protocol.core.methods.response;

import java.io.IOException;
import java.math.BigInteger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.core.Response;
import org.web3j.utils.Numeric;

public class LineaEstimateGas extends Response<LineaEstimateGas.LineaEstimateGasResponse> {

    @Override
    @JsonDeserialize(using = LineaEstimateGas.ResponseDeserialiser.class)
    public void setResult(LineaEstimateGas.LineaEstimateGasResponse result) {
        super.setResult(result);
    }

    public LineaEstimateGas.LineaEstimateGasResponse getLineaEstimateGas() {
        return getResult();
    }

    public static class LineaEstimateGasResponse {
        private BigInteger baseFeePerGas;
        private BigInteger gasLimit;
        private BigInteger priorityFeePerGas;

        public LineaEstimateGasResponse() {}

        public LineaEstimateGasResponse(
                BigInteger baseFeePerGas, BigInteger gasLimit, BigInteger priorityFeePerGas) {
            this.baseFeePerGas = baseFeePerGas;
            this.gasLimit = gasLimit;
            this.priorityFeePerGas = priorityFeePerGas;
        }

        public BigInteger getBaseFeePerGas() {
            return baseFeePerGas;
        }

        public void setBaseFeePerGas(String baseFeePerGas) {
            this.baseFeePerGas = Numeric.decodeQuantity(baseFeePerGas);
        }

        public BigInteger getGasLimit() {
            return gasLimit;
        }

        public void setGasLimit(String gasLimit) {
            this.gasLimit = Numeric.decodeQuantity(gasLimit);
        }

        public BigInteger getPriorityFeePerGas() {
            return priorityFeePerGas;
        }

        public void setPriorityFeePerGas(String priorityFeePerGas) {
            this.priorityFeePerGas = Numeric.decodeQuantity(priorityFeePerGas);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof LineaEstimateGasResponse)) {
                return false;
            }
            LineaEstimateGasResponse res = (LineaEstimateGasResponse) o;

            if (getGasLimit() != null
                    ? !getGasLimit().equals(res.getGasLimit())
                    : res.getGasLimit() != null) {
                return false;
            }

            if (getBaseFeePerGas() != null
                    ? !getBaseFeePerGas().equals(res.getBaseFeePerGas())
                    : res.getBaseFeePerGas() != null) {
                return false;
            }

            return getPriorityFeePerGas() != null
                    ? getPriorityFeePerGas().equals(res.getPriorityFeePerGas())
                    : res.getPriorityFeePerGas() == null;
        }
    }

    public static class ResponseDeserialiser
            extends JsonDeserializer<LineaEstimateGas.LineaEstimateGasResponse> {

        private ObjectReader objectReader = ObjectMapperFactory.getObjectReader();

        @Override
        public LineaEstimateGas.LineaEstimateGasResponse deserialize(
                JsonParser jsonParser, DeserializationContext deserializationContext)
                throws IOException {
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                return objectReader.readValue(
                        jsonParser, LineaEstimateGas.LineaEstimateGasResponse.class);
            } else {
                return null; // null is wrapped by Optional in above getter
            }
        }
    }
}
