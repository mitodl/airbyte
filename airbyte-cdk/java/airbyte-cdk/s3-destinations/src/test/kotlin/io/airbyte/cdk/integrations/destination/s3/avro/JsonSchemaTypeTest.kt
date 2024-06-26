/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */
package io.airbyte.cdk.integrations.destination.s3.avro

import io.airbyte.cdk.integrations.destination.s3.avro.JsonSchemaType.Companion.fromJsonSchemaType
import java.util.stream.Stream
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource

class JsonSchemaTypeTest {
    @ParameterizedTest
    @ArgumentsSource(JsonSchemaTypeProvider::class)
    fun testFromJsonSchemaType(
        type: String,
        airbyteType: String?,
        expectedJsonSchemaType: JsonSchemaType?
    ) {
        Assertions.assertEquals(expectedJsonSchemaType, fromJsonSchemaType(type, airbyteType))
    }

    class JsonSchemaTypeProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    "WellKnownTypes.json#/definitions/Number",
                    null,
                    JsonSchemaType.NUMBER_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/String",
                    null,
                    JsonSchemaType.STRING_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/Integer",
                    null,
                    JsonSchemaType.INTEGER_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/Boolean",
                    null,
                    JsonSchemaType.BOOLEAN_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/BinaryData",
                    null,
                    JsonSchemaType.BINARY_DATA_V1
                ),
                Arguments.of("WellKnownTypes.json#/definitions/Date", null, JsonSchemaType.DATE_V1),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/TimestampWithTimezone",
                    null,
                    JsonSchemaType.TIMESTAMP_WITH_TIMEZONE_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/TimestampWithoutTimezone",
                    null,
                    JsonSchemaType.TIMESTAMP_WITHOUT_TIMEZONE_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/TimeWithTimezone",
                    null,
                    JsonSchemaType.TIME_WITH_TIMEZONE_V1
                ),
                Arguments.of(
                    "WellKnownTypes.json#/definitions/TimeWithoutTimezone",
                    null,
                    JsonSchemaType.TIME_WITHOUT_TIMEZONE_V1
                ),
                Arguments.of("number", "integer", JsonSchemaType.NUMBER_INT_V0),
                Arguments.of("string", "big_integer", JsonSchemaType.NUMBER_BIGINT_V0),
                Arguments.of("number", "float", JsonSchemaType.NUMBER_FLOAT_V0),
                Arguments.of("number", null, JsonSchemaType.NUMBER_V0),
                Arguments.of("string", null, JsonSchemaType.STRING_V0),
                Arguments.of("integer", null, JsonSchemaType.INTEGER_V0),
                Arguments.of("boolean", null, JsonSchemaType.BOOLEAN_V0),
                Arguments.of("null", null, JsonSchemaType.NULL),
                Arguments.of("object", null, JsonSchemaType.OBJECT),
                Arguments.of("array", null, JsonSchemaType.ARRAY),
                Arguments.of("combined", null, JsonSchemaType.COMBINED)
            )
        }
    }
}
