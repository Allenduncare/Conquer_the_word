For Sharing POsts
Idk how u want to do sharing, didn't understand which file had the results and stuff, but here's the code
In the XML file:
//Add this to create the share button
 <com.facebook.share.widget.ShareButton
        android:id="@+id/shareButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginLeft="5dp"
        android:layout_marginRight="5dp"
        android:layout_alignParentStart="true"
        android:layout_toStartOf="@+id/textView2" />

In the java file:
view.setText(resultStr);
//.setContentDescription(put w.e u want to be seen as the post)
//.setUrl(url that gets opened if someone clicks the post)

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        final GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            name[0] = object.getString("first_name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
        ShareButton shareButton = (ShareButton)findViewById(R.id.shareButton);
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("google.com"))
                .setContentDescription("Player has scored " +Right + "right! Play today!")
                .build();
        shareButton.setShareContent(content);
