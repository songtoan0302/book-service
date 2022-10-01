package org.aibles.core_exception.exception;

import org.aibles.core_exception.component.MessageHelper;
import org.springframework.http.HttpStatus;

/**
 * @author toanns
 */
public class InternalServerException extends BaseException {
  private static final String MESSAGE_I18N_INTERNAL_SERVER_ERROR = "message.internal-server-exception";

  public InternalServerException(String error, MessageHelper messageHelper) {
    setCode("org.aibles.okrs.core_exception.exception.InternalServerException");
    setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    addParam(MESSAGE_I18N_INTERNAL_SERVER_ERROR, error);
    setKeyMessage(MESSAGE_I18N_INTERNAL_SERVER_ERROR);
  }
}
