package com.darkstudio.webApp;

import lombok.Getter;

public class Variables {
    @Getter
    String bgImage = "url('https://cdn.dribbble.com/users/192276/screenshots/2500960/attachments/491748/midnightinthevalley-2560x1440.png')";

    public String getBgImage() {
        return bgImage;
    }
}
