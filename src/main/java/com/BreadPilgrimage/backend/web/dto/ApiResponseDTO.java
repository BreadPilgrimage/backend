package com.BreadPilgrimage.backend.web.dto;

import com.BreadPilgrimage.backend.web.dto.BakeryRequestDTO.BakeryDTO;
import java.util.List;

public class ApiResponseDTO {

  private Response response;

  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }

  public static class Response {
    private Body body;

    public Body getBody() {
      return body;
    }

    public void setBody(Body body) {
      this.body = body;
    }
  }

  public static class Body {
    private List<BakeryDTO> items;

    public List<BakeryDTO> getItems() {
      return items;
    }

    public void setItems(List<BakeryDTO> items) {
      this.items = items;
    }
  }
}
