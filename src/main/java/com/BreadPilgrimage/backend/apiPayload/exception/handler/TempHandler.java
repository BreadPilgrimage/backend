package com.BreadPilgrimage.backend.apiPayload.exception.handler;


import com.BreadPilgrimage.backend.apiPayload.code.BaseErrorCode;
import com.BreadPilgrimage.backend.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

  public TempHandler(BaseErrorCode errorCode) {
    super(errorCode);
  }
}
