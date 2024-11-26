package com.slost_only1.slost_only1.data.req;

import com.slost_only1.slost_only1.enums.ReviewReportReason;

public record ReviewReportReq(Long reviewId,
                              ReviewReportReason reportReason,
                              String reportContent) {
}
