package me.chiontang.wechatmomentexport;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import me.chiontang.wechatmomentexport.models.Comment;
import me.chiontang.wechatmomentexport.models.Like;
import me.chiontang.wechatmomentexport.models.Tweet;


import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;

public class Main2 implements IXposedHookLoadPackage {

    Tweet currentTweet = new Tweet();
    ArrayList<Tweet> tweetList = new ArrayList<Tweet>();
    String lastTimelineId = "";
    Thread intervalSaveThread = null;
    Context appContext = null;
    String wechatVersion = "";

    //com.wandoujia.ripple.fragment.FeedListFragment

    @Override
    public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.wandoujia"))
            return;

        XposedBridge.log("handleLoadPackage ===== " + lpparam.packageName);
        Config.enabled = true;
//        Class classCustomOp = XposedHelpers.findClass("com.wandoujia.ripple_framework.model.Model", lpparam.classLoader);
//        Class classDataloadListener = XposedHelpers.findClass("com.wandoujia.nirvana.framework.network.page.DataLoadListener.ˊ", lpparam.classLoader);
//        if(classCustomOp != null) {
//            XposedBridge.log("内部类classCustomOp=" + classCustomOp.getName());
//
//        }else{
//            XposedBridge.log("内部类classCustomOp=null");
//        }
//
//        if(classDataloadListener != null) {
//            XposedBridge.log("内部类classDataloadListener=" + classDataloadListener.getName());
//        }else{
//            XposedBridge.log("内部类classDataloadListener=null");
//        }

//
//        //＝＝＝＝＝＝＝＝＝＝  获取com.wandoujia.api.proto.Entity$Builder的数据  ＝＝＝＝＝＝＝＝＝＝
//        HashMap<String, String> map = getData(lpparam);
//
//
////        for (int i = 0; i < data.size(); i++){
//            XposedBridge.log("data map ===== " + map.toString());
////        }
//
//        //＝＝＝＝＝＝＝＝＝＝  end  ＝＝＝＝＝＝＝＝＝＝


        /**
         * Xposed提供的Hook方法
         *
         * @param className 待Hook的Class
         * @param classLoader classLoader
         * @param methodName 待Hook的Method
         * @param parameterTypesAndCallback hook回调
         * @return
         */
        findAndHookMethod("com.wandoujia.ripple_framework.ripple.fragment.FeedDetailFragment", lpparam.classLoader, "onCreate",
                Bundle.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;
                        XposedBridge.log("FeedDetailFragment  param ＝＝＝＝ " + param);
//                Class entity  = XposedHelpers.findClass("com.wandoujia.api.proto.Entity", lpparam.classLoader);
//                Class builder = XposedHelpers.findClass("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader);
//                Method parseMethod = builder.getMethod("attach_entity", List.class);


                        HashMap<String, String> map = getData(lpparam);
                        XposedBridge.log("data map ===== " + map.toString());


//                String result = (String) parseMethod.invoke(modleCon.newInstance(
//                        new Object[]{entityCon.newInstance(new Object[]{builderCon.newInstance()})}),null);
//                XposedBridge.log("presult ddd＝＝＝＝ ");
//                XposedBridge.log("result ＝＝＝＝ "+result);
//


//                Class model = XposedHelpers.findClass("com.wandoujia.ripple_framework.model.Model", lpparam.classLoader);
//                XposedBridge.log("presult aaa＝＝＝＝ ");
//
//                Class entityClass = XposedHelpers.findClass("com.wandoujia.api.proto.Entity", lpparam.classLoader);
//
//                Class entityBuilderClass = XposedHelpers.findClass("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader);
//                XposedBridge.log("presult bbb＝＝＝＝ ");

//                Method parseMethod = model.getMethod("ˑ");
//                XposedBridge.log("presult ccc＝＝＝＝ ");


//                Constructor modleCon = model.getDeclaredConstructor(new Class[]{entityClass});
//                modleCon.setAccessible(true);
//
//                Constructor entityCon = entityClass.getDeclaredConstructor(new Class[]{entityBuilderClass});
//                entityCon.setAccessible(true);
//
//                Constructor builderCon = entityBuilderClass.getDeclaredConstructor();
//                builderCon.setAccessible(true);
//
//
//
////                abx =(abx)c1.newInstance(new Object[]{model2});
//                String result = (String) parseMethod.invoke(modleCon.newInstance(
//                        new Object[]{entityCon.newInstance(new Object[]{builderCon.newInstance()})}),null);
//                XposedBridge.log("presult ddd＝＝＝＝ ");
//                XposedBridge.log("result ＝＝＝＝ "+result);


//                if(result instanceof CharSequence){
//                    XposedBridge.log("result ＝＝＝＝ "+result.toString());
//                } else if (result instanceof String){
//                    XposedBridge.log("result ＝＝＝＝ "+ result);
//                }

//                XposedBridge.log("result ＝＝＝＝ "+result);
//                XposedBridge.log("param args[0] ＝＝＝＝ "+param.args[0]);
//                int paramFirstInt = (int)param.args[0];
//                int paramFirstInt = (int)param.args[0];
//                switch (paramFirstInt){
//                    case 2131427347:    //图片
//
//                    break;
//                    case 2131427387:    //title
//
//                    break;
//                }


//                File jsonFile = new File(Config.outputFile);
//                if (!jsonFile.exists()) {
//                    try {
//                        jsonFile.createNewFile();
//                    } catch (IOException e) {
////                        XposedBridge.log(e.getMessage());
//                    }
//                }
//                try {
//                    FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
//                    BufferedWriter bw = new BufferedWriter(fw);
//                    XposedBridge.log("Hooked. ");
//                    bw.write("create my file");
//                    Object currentObject = param.thisObject;
//                    bw.newLine();
//                    bw.write(currentObject.toString());
//                    bw.close();
//                } catch (IOException e) {
////                    XposedBridge.log(e.getMessage());
//                }
//                hookMethods(lpparam);
                    }
                });


    }

    public static final String ENTITY_BUILDER = "com.wandoujia.api.proto.Entity$Builder";
    public static final String CARD_PACKAGE = "com.wandoujia.logv3.model.packages.CardPackage$Builder";
    public static final String APK_DETAIL = "ccom.wandoujia.api.proto.ApkDetail$Builder";


    String entityDataArray[] = {"description", "detail_url", "icon", "id_string"
            , "recommend_reason", "snippet", "strategy_name", "sub_title", "summary"
            , "symbol", "tag_line", "template_name", "title"};

//    String cardDataArray[] = {"identity", "parent_id", "status", "sub_status"
//            , "sub_type", "tag", "type"};

//    String apkDetailArray[] = {"ads_type", "md5", "paid_type", "permission_group"
//            , "permission_level", "security_status", "size", "version_name"};
//    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();


    private HashMap<String, String> getData(LoadPackageParam lpparam) {

        final HashMap<String, String> map = new HashMap<String, String>();
        final Class templateType = XposedHelpers.findClass("com.wandoujia.api.proto.TemplateTypeEnum$TemplateType", lpparam.classLoader);

        findAndHookMethod("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader, "template_type",
                templateType, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                        XposedBridge.log(" template_type ＝＝＝＝ " + param.args[0]);
                    }
                });
        findAndHookMethod("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader, "type",
                Integer.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                        XposedBridge.log(" type ＝＝＝＝ " + param.args[0]);
                    }
                });

        findAndHookMethod("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader, "badge",
                Integer.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                        XposedBridge.log(" badge ＝＝＝＝ " + param.args[0]);
                    }
                });
        findAndHookMethod("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader, "section_auto_grid_rows",
                Integer.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                        XposedBridge.log(" section_auto_grid_rows ＝＝＝＝ " + param.args[0]);
                    }
                });
        findAndHookMethod("com.wandoujia.api.proto.Entity$Builder", lpparam.classLoader, "template",
                Integer.class, new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                        Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                        XposedBridge.log(" template ＝＝＝＝ " + param.args[0]);
                    }
                });


        for (int i = 0; i < entityDataArray.length; i++) {

            final int finalI = i;
            findAndHookMethod(ENTITY_BUILDER, lpparam.classLoader, entityDataArray[i],
                    String.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Config.enabled = true;

//                            XposedBridge.log("param ＝＝＝＝ " + param);
//                            XposedBridge.log("param args ＝＝＝＝ " + param.args);
                            XposedBridge.log("param args[0] " + entityDataArray[finalI] + "＝＝＝＝ " + param.args[0]);
                            map.put(entityDataArray[finalI], (String) param.args[0]);

                        }
                    });

        }
        XposedBridge.log("＝＝＝＝ loop over ＝＝＝＝ ");
        return map;
//        data.add(map);

    }

    private void getAllTextViews(final View v) {
        if (v instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) v;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View child = vg.getChildAt(i);
                getAllTextViews(child);
            }
        } else if (v instanceof TextView) {
            String name = ((TextView) v).getText().toString();
            writeTxtToFile(name);
        }
    }


    private void hookMethods(final LoadPackageParam lpparam) {
        findAndHookMethod("com.tencent.mm.storage.c", lpparam.classLoader, "", Cursor.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                super.afterHookedMethod(param);
                //ʿ
//                loadFromSharedPreference();
                Config.enabled = true;

                File jsonFile = new File(Config.outputFile);
                if (!jsonFile.exists()) {
                    try {
                        jsonFile.createNewFile();
                    } catch (IOException e) {
                        XposedBridge.log(e.getMessage());
                    }
                }

                try {
                    FileWriter fw = new FileWriter(jsonFile.getAbsoluteFile());
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write("create my file");

                    Class c = XposedHelpers.findClass("com.tencent.mm.storage.c", lpparam.classLoader);
                    bw.write(c.getName());
                    bw.close();
                } catch (IOException e) {
                    XposedBridge.log(e.getMessage());
                }
            }
        });

    }

    private void ʿ() {

    }

    private void loadFromSharedPreference() {
        XSharedPreferences pref = new XSharedPreferences(Main2.class.getPackage().getName(), "config");
        pref.makeWorldReadable();
        pref.reload();
        Config.enabled = pref.getBoolean("enabled", false);
        Config.outputFile = pref.getString("outputFile", Environment.getExternalStorageDirectory() + "/moments_output.json");
    }

    private String getTimelineId(String xmlResult) {
        Pattern idPattern = Pattern.compile("<id><!\\[CDATA\\[(.+?)\\]\\]></id>");
        Matcher idMatcher = idPattern.matcher(xmlResult);
        if (idMatcher.find()) {
            return idMatcher.group(1);
        } else {
            return "";
        }
    }

    private void parseTimelineXML(String xmlResult) throws Throwable {
        Pattern userIdPattern = Pattern.compile("<username><!\\[CDATA\\[(.+?)\\]\\]></username>");
        Pattern contentPattern = Pattern.compile("<contentDesc><!\\[CDATA\\[(.+?)\\]\\]></contentDesc>", Pattern.DOTALL);
        Pattern mediaPattern = Pattern.compile("<media>.*?<url.*?><!\\[CDATA\\[(.+?)\\]\\]></url>.*?</media>");
        Pattern timestampPattern = Pattern.compile("<createTime><!\\[CDATA\\[(.+?)\\]\\]></createTime>");

        Matcher userIdMatcher = userIdPattern.matcher(xmlResult);
        Matcher contentMatcher = contentPattern.matcher(xmlResult);
        Matcher mediaMatcher = mediaPattern.matcher(xmlResult);
        Matcher timestampMatcher = timestampPattern.matcher(xmlResult);

        currentTweet.id = getTimelineId(xmlResult);

        currentTweet.rawXML = xmlResult;

        if (timestampMatcher.find()) {
            currentTweet.timestamp = Integer.parseInt(timestampMatcher.group(1));
        }

        if (userIdMatcher.find()) {
            currentTweet.authorId = userIdMatcher.group(1);
        }

        if (contentMatcher.find()) {
            currentTweet.content = contentMatcher.group(1);
        }

        while (mediaMatcher.find()) {
            boolean flag = true;
            for (int i = 0; i < currentTweet.mediaList.size(); i++) {
                if (currentTweet.mediaList.get(i).equals(mediaMatcher.group(1))) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                currentTweet.mediaList.add(mediaMatcher.group(1));
        }

    }

    private void parseSnsObject(Object aqiObject) throws Throwable {
        Tweet matchTweet = null;
        Field field = null;
        Object userId = null, nickname = null;

        field = aqiObject.getClass().getField(Config.PROTOCAL_SNS_OBJECT_USERID_FIELD);
        userId = field.get(aqiObject);

        field = aqiObject.getClass().getField(Config.PROTOCAL_SNS_OBJECT_NICKNAME_FIELD);
        nickname = field.get(aqiObject);

        field = aqiObject.getClass().getField(Config.PROTOCAL_SNS_OBJECT_TIMESTAMP_FIELD);
        long snsTimestamp = ((Integer) field.get(aqiObject)).longValue();

        if (userId == null || nickname == null) {
            return;
        }

        for (int i = 0; i < tweetList.size(); i++) {
            Tweet tweet = tweetList.get(i);
            if (tweet.timestamp == snsTimestamp && tweet.authorId.equals((String) userId)) {
                matchTweet = tweet;
                break;
            }
        }

        if (matchTweet == null) {
            return;
        }

        matchTweet.ready = true;
        matchTweet.author = (String) nickname;
        field = aqiObject.getClass().getField(Config.PROTOCAL_SNS_OBJECT_COMMENTS_FIELD);
        LinkedList list = (LinkedList) field.get(aqiObject);
        for (int i = 0; i < list.size(); i++) {
            Object childObject = list.get(i);
            parseSnsObjectExt(childObject, true, matchTweet);
        }

        field = aqiObject.getClass().getField(Config.PROTOCAL_SNS_OBJECT_LIKES_FIELD);
        LinkedList likeList = (LinkedList) field.get(aqiObject);
        for (int i = 0; i < likeList.size(); i++) {
            Object likeObject = likeList.get(i);
            parseSnsObjectExt(likeObject, false, matchTweet);
        }
        matchTweet.print();

    }

    private void parseSnsObjectExt(Object apzObject, boolean isComment, Tweet matchTweet) throws Throwable {
        if (isComment) {
            Field field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_AUTHOR_NAME_FIELD);
            Object authorName = field.get(apzObject);

            field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_REPLY_TO_FIELD);
            Object replyToUserId = field.get(apzObject);

            field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_COMMENT_FIELD);
            Object commentContent = field.get(apzObject);

            field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_AUTHOR_ID_FIELD);
            Object authorId = field.get(apzObject);

            if (authorId == null || commentContent == null || authorName == null) {
                return;
            }

            for (int i = 0; i < matchTweet.comments.size(); i++) {
                Comment loadedComment = matchTweet.comments.get(i);
                if (loadedComment.authorId.equals((String) authorId) && loadedComment.content.equals((String) commentContent)) {
                    return;
                }
            }

            Comment newComment = new Comment();
            newComment.authorName = (String) authorName;
            newComment.content = (String) commentContent;
            newComment.authorId = (String) authorId;
            newComment.toUserId = (String) replyToUserId;

            for (int i = 0; i < matchTweet.comments.size(); i++) {
                Comment loadedComment = matchTweet.comments.get(i);
                if (replyToUserId != null && loadedComment.authorId.equals((String) replyToUserId)) {
                    newComment.toUser = loadedComment.authorName;
                    break;
                }
            }

            matchTweet.comments.add(newComment);
        } else {
            Field field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_AUTHOR_NAME_FIELD);
            Object nickname = field.get(apzObject);
            field = apzObject.getClass().getField(Config.SNS_OBJECT_EXT_AUTHOR_ID_FIELD);
            Object userId = field.get(apzObject);
            if (nickname == null || userId == null) {
                return;
            }

            if (((String) userId).equals("")) {
                return;
            }

            for (int i = 0; i < matchTweet.likes.size(); i++) {
                if (matchTweet.likes.get(i).userId.equals((String) userId)) {
                    return;
                }
            }
            Like newLike = new Like();
            newLike.userId = (String) userId;
            newLike.userName = (String) nickname;
            matchTweet.likes.add(newLike);
        }
    }

    private void addTweetToListNoRepeat() {
        if (currentTweet.id.equals("")) {
            return;
        }
        int replaceIndex = -1;
        for (int i = 0; i < tweetList.size(); i++) {
            Tweet loadedTweet = tweetList.get(i);
            if (loadedTweet.id.equals(currentTweet.id)) {
                replaceIndex = i;
                break;
            }
        }

        Tweet tweetToAdd = currentTweet.clone();
        if (replaceIndex == -1) {
            tweetList.add(tweetToAdd);
        } else {
            tweetList.remove(replaceIndex);
            tweetList.add(replaceIndex, tweetToAdd);
        }

    }


    // 将字符串写入到文本文件中
    public void writeTxtToFile(String name) {

        XposedBridge.log("---------------------");
        XposedBridge.log(name);
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }

    }
}