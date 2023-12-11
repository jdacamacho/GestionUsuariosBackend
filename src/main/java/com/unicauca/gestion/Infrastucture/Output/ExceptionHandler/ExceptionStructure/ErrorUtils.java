package com.unicauca.gestion.Infrastucture.Output.ExceptionHandler.ExceptionStructure;

public final class ErrorUtils {

  ErrorUtils() {

  }

  /**
   * Crea un nuevo objeto de <code>Error</code>
   * 
   * @param errorCode
   * @param messageKey
   * @param httpCode
   * @return - Objeto creado
   */
  public static Error createError(final String errorCode, final String messageKey, final Integer httpCode) {
    final Error error = new Error();
    error.setErrorCode(errorCode);
    error.setMessage(messageKey);
    error.setHttpCode(httpCode);
    return error;
  }
}

