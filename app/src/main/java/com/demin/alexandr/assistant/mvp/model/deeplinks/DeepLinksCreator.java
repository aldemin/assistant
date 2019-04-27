package com.demin.alexandr.assistant.mvp.model.deeplinks;

import android.net.Uri;

import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class DeepLinksCreator {

    private String DYNAMIC_LINK_DOMAIN = "assistant.app.goo.gl";
    private String PARAM_PASS = "passId";

    public Uri getNewLink(String passId) {
        return createDynamicUri(createShareUri(passId));
    }

    private Uri createShareUri(String passId) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("j93jrv9jsdsdf.xyz")
                .appendPath("passId")
                .appendQueryParameter(PARAM_PASS, passId);
        return builder.build();
    }

    private Uri createDynamicUri(Uri shareUri){
        DynamicLink dynamicLink = FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(shareUri)
                .setDomainUriPrefix(DYNAMIC_LINK_DOMAIN)
                .setAndroidParameters(new DynamicLink.AndroidParameters.Builder().build())
                .buildDynamicLink();
        return dynamicLink.getUri();
    }

}
