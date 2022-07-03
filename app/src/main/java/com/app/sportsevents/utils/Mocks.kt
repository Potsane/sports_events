package com.app.sportsevents.utils

import com.app.sportsevents.network.entity.SportEvent

fun getMocks(): List<SportEvent> {
    return listOf(
        SportEvent(
            "1",
            "Liverpool v Porto",
            "UEFA Champions League",
            "2022-07-01T05:36:14.027Z",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
        ),
        SportEvent(
            "1",
            "Liverpool v Porto",
            "UEFA Champions League",
            "2022-07-01T05:36:14.027Z",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
        ),
        SportEvent(
            "1",
            "Liverpool v Porto",
            "UEFA Champions League",
            "2022-07-01T05:36:14.027Z",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310381637057_image-header_pDach_1554664873000.jpeg?alt=media&token=53616931-55a8-476e-b1b7-d18fc22a2bf0",
            "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
        )
    )
}