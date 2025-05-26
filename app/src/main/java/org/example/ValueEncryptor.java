package org.example;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Base64;

@Converter
public class ValueEncryptor implements AttributeConverter<String, String> {
  private static final Base64.Decoder decoder = Base64.getDecoder();
  private static final Base64.Encoder encoder = Base64.getEncoder();

  @Override
  public String convertToDatabaseColumn(final String attribute) {
    if (attribute == null) {
      return null;
    }
    var encoded = encoder.encodeToString(attribute.getBytes());
    System.out.println("'encrypt' > from '" + attribute + "' to: '" + encoded + "'");
    return encoded;
  }

  @Override
  public String convertToEntityAttribute(final String dbData) {
    if (dbData == null) {
      return null;
    }
    var string = new String(decoder.decode(dbData));
    System.out.println("'decrypt' < from '" + dbData + "' to: '" + string + "'");
    return string;
  }
}
