package com.slost_only1.slost_only1.data.req;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.coyote.BadRequestException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DolbomNoticeReq {
    private String sido;

    private String bname;

    private String sigungu;

    public void isValid() throws BadRequestException {
        if (!StringUtils.isEmpty(sido) && !StringUtils.isEmpty(bname) && !StringUtils.isEmpty(sigungu)) {
            throw new BadRequestException();
        }
    }

}
