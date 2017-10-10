package com.example.ggxiaozhi.store.the_basket.utils;

import com.example.ggxiaozhi.store.the_basket.bean.AppBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppCommentBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppDetailBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppIntroductionBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppMoreRecommendBean;
import com.example.ggxiaozhi.store.the_basket.bean.AppRecommendBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNecessaryBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryNewBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategorySubscribeBean;
import com.example.ggxiaozhi.store.the_basket.bean.CategoryToolBean;
import com.example.ggxiaozhi.store.the_basket.bean.RecommendBean;
import com.example.ggxiaozhi.store.the_basket.bean.TopBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工程名 ： BaiLan
 * 包名   ： com.example.ggxiaozhi.store.the_basket.bean
 * 作者名 ： 志先生_
 * 日期   ： 2017/9/1
 * 时间   ： 15:59
 * 功能   ：网络请求数据转化赋值再传入相应模块的Bean对象
 */

public class JsonParseUtils {
    public static RecommendBean parseRecommendBean(String json) {
        RecommendBean recommendBean = null;
        List<String> bannerList = new ArrayList<>();
        List<RecommendBean.RecommendAppBean> recommendAppBeanList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray data = jsonObject.getJSONArray("layoutData");
            //轮播图数据
            JSONArray bannerJson = data.getJSONObject(0).getJSONArray("dataList");
            for (int i = 0; i < bannerJson.length(); i++) {
                bannerList.add(bannerJson.getJSONObject(i).getString("icon"));
            }

            for (int m = 2; m < data.length(); m++) {
                if (m == 4 || m == 7) {
                    //广告
                    JSONArray dataList = data.getJSONObject(m).getJSONArray("dataList");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < dataList.length(); i++) {
                        String icon = dataList.getJSONObject(i).getString("icon");
                        list.add(icon);
                    }
                    RecommendBean.RecommendAppBean recommendAppBean = new RecommendBean.RecommendAppBean(null, list, null, 1);
                    recommendAppBeanList.add(recommendAppBean);
                } else {
                    JSONObject jsonData = data.getJSONObject(m).getJSONArray("dataList").getJSONObject(0);
                    String titleName = jsonData.getString("name");
                    JSONArray appList = jsonData.getJSONArray("list");
                    List<AppBean> recommendAppList = new ArrayList<>();
                    for (int i = 0; i < appList.length(); i++) {
                        JSONObject appJson = appList.getJSONObject(i);
                        recommendAppList.add(parseAppBean(appJson.toString()));
                    }
                    RecommendBean.RecommendAppBean recommendAppBean = new RecommendBean.RecommendAppBean(titleName, null, recommendAppList, 0);
                    recommendAppBeanList.add(recommendAppBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        recommendBean = new RecommendBean(bannerList, recommendAppBeanList);
        return recommendBean;
    }

    public static AppBean parseAppBean(String json) {
        AppBean appBean = null;
        String appId = "";
        String appVersionName = "";
        String downCountDesc = "";
        String downurl = "";
        String icon = "";
        String intro = "";
        String memo = "";
        String name = "";
        String packageName = "";
        String sizeDesc = "";
        String stars = "";
        String aliasName = "";
        String detailId = "";

        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("appid")) appId = jsonObject.getString("appid");
            if (jsonObject.has("appVersionName"))
                appVersionName = jsonObject.getString("appVersionName");
            if (jsonObject.has("downCountDesc"))
                downCountDesc = jsonObject.getString("downCountDesc");
            if (jsonObject.has("downurl")) downurl = jsonObject.getString("downurl");
            if (jsonObject.has("icon")) icon = jsonObject.getString("icon");
            if (jsonObject.has("intro")) intro = jsonObject.getString("intro");
            if (jsonObject.has("memo")) memo = jsonObject.getString("memo");
            if (jsonObject.has("name")) name = jsonObject.getString("name");
            if (jsonObject.has("package")) packageName = jsonObject.getString("package");
            if (jsonObject.has("sizeDesc")) sizeDesc = jsonObject.getString("sizeDesc");
            if (jsonObject.has("stars")) stars = jsonObject.getString("stars");
            if (jsonObject.has("aliasName"))
                aliasName = jsonObject.getString("aliasName").substring(0, 1);
            if (jsonObject.has("detailId"))
                detailId = jsonObject.getString("detailId").substring(5);

            appBean = new AppBean(appId, appVersionName, downCountDesc, downurl, icon, intro, memo, name, packageName, sizeDesc, stars, aliasName, detailId);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return appBean;
    }

    public static CategoryBean parseCategoryBean(String json) {

        CategoryBean categoryBean = null;
        try {
            List<CategoryBean.CategoryTopBean> categoryTopBeanList = new ArrayList<>();
            List<CategoryBean.CategoryDataBean> categoryDataBeanList = new ArrayList<>();
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("layoutData");
            //头GridView
            JSONArray topData = data.getJSONObject(2).getJSONArray("dataList");
            for (int i = 0; i < topData.length(); i++) {
                String iconUrl = topData.getJSONObject(i).getString("icon");
                String name = topData.getJSONObject(i).getString("name");
                CategoryBean.CategoryTopBean topBean = new CategoryBean.CategoryTopBean(iconUrl, name);
                categoryTopBeanList.add(topBean);
            }

            //标题
            String title = data.getJSONObject(3).getJSONArray("dataList").getJSONObject(0).getString("name");

            //数据
            JSONArray categoryData = data.getJSONObject(4).getJSONArray("dataList");
            for (int i = 0; i < categoryData.length(); i++) {
                String iconUrl = categoryData.getJSONObject(i).getString("icon");
                String name = categoryData.getJSONObject(i).getString("name");
                CategoryBean.CategoryDataBean bean = new CategoryBean.CategoryDataBean(iconUrl, name);
                categoryDataBeanList.add(bean);
            }
            categoryBean = new CategoryBean(title, categoryTopBeanList, categoryDataBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryBean;
    }

    /*将排行页数据分成标题四栏和各个分类*/
    public static TopBean parseTopBean(String json) {

        TopBean topBean = null;
        try {
            JSONObject data = new JSONObject(json);
            Map<String, List<AppBean>> appBeanMap = getAppBeanMap(data);
            List<TopBean.TopTopBean> topTopBeanList = getTopTopBeanList(data);
            topBean = new TopBean(appBeanMap, topTopBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return topBean;
    }

    /*得到分类栏目和子项的数据*/
    private static Map<String, List<AppBean>> getAppBeanMap(JSONObject json) {
        Map<String, List<AppBean>> appBeanMap = new HashMap<>();
        try {
            JSONArray data = json.getJSONArray("layoutData");
            for (int i = 0; i < data.length(); i++) {
                if (i == 4 || i == 6 || i == 8 || i == 10 | i == 12) {
                    String type = data.getJSONObject(i - 1).getJSONArray("dataList").getJSONObject(0).getString("name");
                    JSONArray appArray = data.getJSONObject(i).getJSONArray("dataList");
                    List<AppBean> appBeanList = new ArrayList<>();
                    for (int m = 0; m < appArray.length(); m++) {
                        JSONObject jsonObject = appArray.getJSONObject(m);
                        AppBean appBean = parseAppBean(jsonObject.toString());
                        appBeanList.add(appBean);
                    }
                    appBeanMap.put(type, appBeanList);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appBeanMap;
    }

    /*得到标题四栏(应用，游戏，付费，其他)的数据*/
    private static List<TopBean.TopTopBean> getTopTopBeanList(JSONObject json) {
        List<TopBean.TopTopBean> topTopBeanList = new ArrayList<>();
        try {
            JSONArray data = json.getJSONArray("layoutData").getJSONObject(2).getJSONArray("dataList");
            for (int i = 0; i < data.length(); i++) {
                String name = data.getJSONObject(i).getString("name");
                String iconUrl = data.getJSONObject(i).getString("icon");
                TopBean.TopTopBean bean = new TopBean.TopTopBean(iconUrl, name);
                topTopBeanList.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return topTopBeanList;
    }

    /**
     * 将推荐页面的中的详情页面请求的返回的json解析成bean对象
     *
     * @param json 数据请求放回的json
     * @return
     */
    public static AppDetailBean parseAppDetailBean(String json) {
        AppDetailBean appDetailBean = null;
        try {
            System.out.println("json:" + json);
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("layoutData");
            JSONObject dataList = data.getJSONObject(0).getJSONArray("dataList").getJSONObject(0);
            String icoUrl = dataList.getString("icoUri");
            String name = dataList.getString("name");
            String intro = dataList.getString("intro");
            String stars = dataList.getString("stars");

            JSONArray labelNames = dataList.getJSONArray("labelNames");
            List<AppDetailBean.LabelName> labelNameList = new ArrayList<AppDetailBean.LabelName>();
            for (int i = 0; i < labelNames.length(); i++) {
                String label = labelNames.getJSONObject(i).getString("name");
                int type = labelNames.getJSONObject(i).getInt("type");
                AppDetailBean.LabelName labelName = new AppDetailBean.LabelName(label, type);
                labelNameList.add(labelName);
            }
            List<AppDetailBean.SafeLabel> safeLabelList = new ArrayList<AppDetailBean.SafeLabel>();
            JSONArray safeLabels = dataList.getJSONArray("safeLabels");
            for (int i = 0; i < safeLabels.length(); i++) {
                JSONObject safe = safeLabels.getJSONObject(i);
                String detectorDesc = "";
                String detectorName = "";
                if (safe.has("detectorDesc")) {
                    detectorDesc = safe.getString("detectorDesc");
                }
                if (safe.has("detectorName")) {
                    detectorName = safe.getString("detectorName");
                }
                String name1 = safe.getString("name");
                String url = safe.getString("url");
                AppDetailBean.SafeLabel safeLabel = new AppDetailBean.SafeLabel(detectorDesc, detectorName, name1, url);
                safeLabelList.add(safeLabel);
            }

            String size = data.getJSONObject(2).getJSONArray("dataList").getJSONObject(0).getString("size");

            JSONArray tabInfo = obj.getJSONArray("tabInfo");
            List<String> tabInfoList = new ArrayList<String>();
            for (int i = 0; i < tabInfo.length(); i++) {
                String tabName = tabInfo.getJSONObject(i).getString("tabName");
                tabInfoList.add(tabName);
            }
            String downUrl = data.getJSONObject(data.length() - 1).getJSONArray("dataList").getJSONObject(0).getString("downurl");


            appDetailBean = new AppDetailBean(name, intro, icoUrl, stars, labelNameList, safeLabelList, tabInfoList, size, downUrl);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appDetailBean;
    }

    /**
     * 详情页 介绍Fragment页面数据
     *
     * @param json
     * @return
     */
    public static AppIntroductionBean parseAppIntroductionBean(String json) {
        AppIntroductionBean appIntroductionBean = null;
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray data = obj.getJSONArray("layoutData");
            JSONObject imageJson = data.getJSONObject(1).getJSONArray("dataList").getJSONObject(0);
            List<String> imageCompressList = new ArrayList<>();
            List<String> imagesList = new ArrayList<>();

            JSONArray imageCompress = imageJson.getJSONArray("imageCompress");
            JSONArray images = imageJson.getJSONArray("images");
            for (int i = 0; i < imageCompress.length(); i++) {
                String imageCompressUrl = imageCompress.getString(i);
                imageCompressList.add(imageCompressUrl);
            }
            for (int i = 0; i < images.length(); i++) {
                String imageUrl = images.getString(i);
                imagesList.add(imageUrl);
            }


            JSONObject dataList = data.getJSONObject(2).getJSONArray("dataList").getJSONObject(0);
            AppIntroductionBean.AppInfoBean appInfoBean = null;
            String developer = dataList.getString("developer");
            String releaseDate = dataList.getString("releaseDate");
            String size = dataList.getString("size");
            String tariffDesc = dataList.getString("tariffDesc");
            String version = dataList.getString("version");
            appInfoBean = new AppIntroductionBean.AppInfoBean(developer, releaseDate, size, tariffDesc, version);


            List<AppIntroductionBean.AppDetailInfoBean> appDetailBeanList = new ArrayList<>();
            List<String> tagList = new ArrayList<>();
            for (int i = 3; i < data.length() - 1; i++) {
                JSONObject dataJson = data.getJSONObject(i).getJSONArray("dataList").getJSONObject(0);
                if (dataJson.has("body") && dataJson.has("title")) {
                    String body = dataJson.getString("body");
                    String title = dataJson.getString("title");
                    AppIntroductionBean.AppDetailInfoBean appDetailInfoBean = new AppIntroductionBean.AppDetailInfoBean(body, title);
                    appDetailBeanList.add(appDetailInfoBean);
                }
                if (dataJson.has("appIntro")) {
                    String body = dataJson.getString("appIntro");
                    AppIntroductionBean.AppDetailInfoBean appDetailInfoBean = new AppIntroductionBean.AppDetailInfoBean(body, "应用介绍");
                    appDetailBeanList.add(appDetailInfoBean);
                }
                if (dataJson.has("tagList")) {
                    JSONArray tagList1 = dataJson.getJSONArray("tagList");
                    for (int m = 0; m < tagList1.length(); m++) {
                        tagList.add(tagList1.getJSONObject(m).getString("tag"));
                    }
                }
            }

            appIntroductionBean = new AppIntroductionBean(imageCompressList, imagesList, appInfoBean, tagList, appDetailBeanList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return appIntroductionBean;
    }

    /*评论部分数据*/
    public static AppCommentBean parseAppCommentBean(String json) {
        AppCommentBean appCommentBean = null;
        try {
            JSONObject obj = new JSONObject(json);
            String score = obj.getString("score");
            String stars = obj.getString("stars");
            JSONArray ratList = obj.getJSONArray("ratingDstList");
            List<AppCommentBean.RatingDstListBean> ratingDstList = new ArrayList<>();
            for (int i = 0; i < ratList.length(); i++) {
                JSONObject jsonObject = ratList.getJSONObject(i);
                int rating = jsonObject.getInt("rating");
                int ratingCounts = jsonObject.getInt("ratingCounts");
                AppCommentBean.RatingDstListBean ratingDstListBean = new AppCommentBean.RatingDstListBean(rating, ratingCounts);
                ratingDstList.add(ratingDstListBean);
            }
            List<AppCommentBean.CommentsBean> comments = new ArrayList<>();
            JSONArray hotList = obj.getJSONArray("hotList");
            for (int i = 0; i < hotList.length(); i++) {
                JSONObject jsonObject = hotList.getJSONObject(i);
                String accountId = jsonObject.getString("accountId");
                String accountName = jsonObject.getString("accountName");
                String approveCounts = jsonObject.getString("approveCounts");
                String commentId = jsonObject.getString("commentId");
                String commentInfo = jsonObject.getString("commentInfo");
                String id = jsonObject.getString("id");
                String operTime = jsonObject.getString("operTime");
                String phone = jsonObject.getString("phone");
                String photoUrl = jsonObject.getString("photoUrl");
                String rating = jsonObject.getString("rating");
                String replyCounts = jsonObject.getString("replyCounts");
                String versionName = jsonObject.getString("versionName");
                String starsComment = jsonObject.getString("stars");

                AppCommentBean.CommentsBean commentsBean = new AppCommentBean.CommentsBean(accountId, accountName, approveCounts, commentId, commentInfo, "1", id, operTime, phone, photoUrl, rating, replyCounts, starsComment, versionName);
                comments.add(commentsBean);
            }
            JSONArray list = obj.getJSONArray("list");
            for (int i = 0; i < list.length(); i++) {
                JSONObject jsonObject = list.getJSONObject(i);
                String accountId = jsonObject.getString("accountId");
                String accountName = jsonObject.getString("accountName");
                String approveCounts = jsonObject.getString("approveCounts");
                String commentId = jsonObject.getString("commentId");
                String commentInfo = jsonObject.getString("commentInfo");
                String id = jsonObject.getString("id");
                String operTime = jsonObject.getString("operTime");
                String phone = jsonObject.getString("phone");
                String photoUrl = jsonObject.getString("photoUrl");
                String rating = jsonObject.getString("rating");
                String replyCounts = jsonObject.getString("replyCounts");
                String versionName = jsonObject.getString("versionName");
                String starsComment = jsonObject.getString("stars");

                AppCommentBean.CommentsBean commentsBean = new AppCommentBean.CommentsBean(accountId, accountName, approveCounts, commentId, commentInfo, "0", id, operTime, phone, photoUrl, rating, replyCounts, starsComment, versionName);
                comments.add(commentsBean);
            }

            appCommentBean = new AppCommentBean(score, stars, ratingDstList, comments);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appCommentBean;
    }

    /*详情页 推荐数据*/
    public static AppRecommendBean parseAppRecommendBean(String json) {
        AppRecommendBean appRecommendBean = null;
        try {

            List<AppBean> popularAppBeanList = new ArrayList<>();
            List<AppBean> tasteAppBeanList = new ArrayList<>();
            List<AppBean> hotAppBeanList = new ArrayList<>();

            JSONObject obj = new JSONObject(json);
            JSONObject popularData = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList").getJSONObject(0);
            JSONArray popularList = popularData.getJSONArray("list");
            for (int i = 0; i < popularList.length(); i++) {
                JSONObject jsonObject = popularList.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject.toString());

                popularAppBeanList.add(appBean);
            }
            JSONObject tasteData = obj.getJSONArray("layoutData").getJSONObject(1).getJSONArray("dataList").getJSONObject(0);
            JSONArray tasteList = tasteData.getJSONArray("list");
            for (int i = 0; i < tasteList.length(); i++) {
                JSONObject jsonObject = tasteList.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject.toString());
                tasteAppBeanList.add(appBean);
            }
            JSONObject hotData = obj.getJSONArray("layoutData").getJSONObject(1).getJSONArray("dataList").getJSONObject(1);
            JSONArray hotList = hotData.getJSONArray("list");
            for (int i = 0; i < hotList.length(); i++) {
                JSONObject jsonObject = hotList.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject.toString());
                hotAppBeanList.add(appBean);
            }

            appRecommendBean = new AppRecommendBean(popularAppBeanList, tasteAppBeanList, hotAppBeanList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appRecommendBean;
    }

    /*预约model*/
    public static CategorySubscribeBean parseCategorySubscribeBean(String json) {
        CategorySubscribeBean categorySubscribeBean = null;
        try {
            List<AppBean> subscribeBeanList = new ArrayList<>();
            JSONObject obj = new JSONObject(json);

            JSONArray jsonArray = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject.toString());

                subscribeBeanList.add(appBean);
            }

            categorySubscribeBean = new CategorySubscribeBean(subscribeBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categorySubscribeBean;
    }

    /*详情页 推荐SubTab 更多数据*/
    public static AppMoreRecommendBean parseAppMoreRecommendBean(String json) {
        AppMoreRecommendBean appMoreRecommendBean = null;
        try {
            JSONObject obj = new JSONObject(json);
            List<AppBean> moreRecommendBeanList = new ArrayList<>();
            JSONArray jsonArray = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject.toString());
                moreRecommendBeanList.add(appBean);
            }

            appMoreRecommendBean = new AppMoreRecommendBean(moreRecommendBeanList);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return appMoreRecommendBean;
    }

    /*分类 必备数据*/
    public static CategoryNecessaryBean parseCategoryNecessaryBean(String json) {
        CategoryNecessaryBean categoryNecessaryBean = null;

        try {
            JSONObject obj = new JSONObject(json);
            CategoryNecessaryBean.Head head = null;
            List<AppBean> appBeanList = new ArrayList<>();
            JSONObject jsonObject = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList").getJSONObject(0);
            String icon = jsonObject.getString("icon");
            String intro = jsonObject.getString("intro");
            head = new CategoryNecessaryBean.Head(icon, intro);

            JSONArray jsonArray = obj.getJSONArray("layoutData").getJSONObject(1).getJSONArray("dataList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject1.toString());
                appBeanList.add(appBean);
            }

            categoryNecessaryBean = new CategoryNecessaryBean(head, appBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryNecessaryBean;
    }


    /*分类Fragment中的首发页面数据*/
    public static CategoryNewBean parseCategoryNewBean(String json) {
        CategoryNewBean categoryNewBean = null;
        try {
            JSONObject obj = new JSONObject(json);
            CategoryNewBean.Head head = null;
            List<AppBean> appBeanList = new ArrayList<>();
            JSONObject jsonObject = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList").getJSONObject(0);
            String icon = jsonObject.getString("icon");
            String intro = jsonObject.getString("intro");
            head = new CategoryNewBean.Head(icon, intro);

            JSONArray jsonArray = obj.getJSONArray("layoutData").getJSONObject(1).getJSONArray("dataList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                AppBean appBean = parseAppBean(jsonObject1.toString());
                appBeanList.add(appBean);
            }

            categoryNewBean = new CategoryNewBean(head, appBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryNewBean;
    }

    /*分类Fragment中的专题页面数据*/
    public static List<String> parseCategorySubject(String json) {
        List<String> list = new ArrayList<>();
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("layoutData").getJSONObject(0).getJSONArray("dataList");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                list.add(jsonObject.getString("icon"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /*分类Fragment中各类别页面数据*/
    public static CategoryToolBean parseCategoryToolBean(String json) {
        CategoryToolBean categoryToolBean = null;

        List<String> bannerList = new ArrayList<>();
        List<CategoryToolBean.CategoryToolAppBean> reacommendAppBeanList = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(json);

            JSONArray data = obj.getJSONArray("layoutData");
            //轮播图数据
            JSONArray bannerJson = data.getJSONObject(0).getJSONArray("dataList");
            for (int i = 0; i < bannerJson.length(); i++) {
                bannerList.add(bannerJson.getJSONObject(i).getString("icon"));
            }

            for (int m = 1; m < data.length(); m++) {
                if (m == 3) {
                    //广告
                    JSONArray dataList = data.getJSONObject(m).getJSONArray("dataList");
                    List<String> list = new ArrayList<>();
                    for (int i = 0; i < dataList.length(); i++) {
                        String icon = dataList.getJSONObject(i).getString("icon");
                        list.add(icon);
                    }
                    CategoryToolBean.CategoryToolAppBean recommendAppBean = new CategoryToolBean.CategoryToolAppBean(null, list, null, 1);
                    reacommendAppBeanList.add(recommendAppBean);
                } else {
                    JSONObject jsonData = data.getJSONObject(m).getJSONArray("dataList").getJSONObject(0);
                    String titleName = jsonData.getString("name");
                    JSONArray appList = jsonData.getJSONArray("list");
                    List<AppBean> recommendAppList = new ArrayList<>();
                    for (int i = 0; i < appList.length(); i++) {
                        appList.getJSONObject(i);
                        AppBean appBean = parseAppBean(appList.getJSONObject(i).toString());

                        recommendAppList.add(appBean);
                    }
                    CategoryToolBean.CategoryToolAppBean recommendAppBean = new CategoryToolBean.CategoryToolAppBean(titleName, null, recommendAppList, 0);
                    reacommendAppBeanList.add(recommendAppBean);
                }
            }
            categoryToolBean = new CategoryToolBean(bannerList, reacommendAppBeanList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryToolBean;
    }
}
