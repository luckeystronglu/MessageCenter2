package com.luckey.messagecenter;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.protobuf.InvalidProtocolBufferException;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.luckey.adapter.MsgAdapter;
import com.luckey.base.BaseOFragment;
import com.luckey.model.Blog;
import com.luckey.model.Image;
import com.luckey.request_respose.GetBlogListRequest;
import com.luckey.request_respose.GetBlogListResponse;
import com.luckey.socket.SocketAppPacket;
import com.luckey.socket.SocketClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/12/22.
 */
public class SafetyFragment extends BaseOFragment implements AdapterView.OnItemClickListener {
    private MsgAdapter msgAdapter;
    public PullToRefreshListView lv;
//    public SwipeRefreshLayout srl;
    List<Blog> listBlog;
//    private int getDataType = 1; //1初始加载  2表示上拉加载
    private int getDataType = 0;//0初始加载 1表示下拉刷新 2表示上拉加载
    private int pageNum = -1;
    private boolean isLastPage = false;

    @Override
    public int getContentId() {
        return R.layout.fragment_message_safety;
    }

    @Override
    protected void init(View view) {
        listBlog = new ArrayList<>();
        lv = findViewByIds(R.id.msg_lv);
        lv.setMode(PullToRefreshBase.Mode.BOTH);// 同时支持上拉下拉
//        srl = findViewByIds(srl);
        msgAdapter = new MsgAdapter(getActivity());
        lv.setAdapter(msgAdapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    protected void loadDatas() {
        getDataType = 0;
        pageNum = 1;
        doSocket();
    }


    @Override
    protected void bindListener() {
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                setUpdateTime(refreshView);
                isLastPage = false;
                getDataType = 1;
                pageNum = 1;

                lv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lv.onRefreshComplete();
                    }
                }, 1000);

                doSocket();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (isLastPage) {
                    Toast.makeText(getActivity(),"已经最后一页了",Toast.LENGTH_SHORT);
                    lv.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            lv.onRefreshComplete();
                        }
                    }, 1000);
                    return;
                }
                setUpdateTime(refreshView);
                getDataType = 2;
                pageNum++;

                lv.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        lv.onRefreshComplete();
                    }
                }, 1000);

                doSocket();

            }
        });
        initListViewTipText();

    }

    /**
     * 初始化列表刷新时的提示文本
     */
    private void initListViewTipText() {
        // 设置上拉刷新文本
        ILoadingLayout startLabels = lv.getLoadingLayoutProxy(true,
                false);
        startLabels.setPullLabel(getResources().getString(R.string.pull_down_refresh));
        startLabels.setReleaseLabel(getResources().getString(R.string.release_refresh));
        startLabels.setRefreshingLabel(getResources().getString(R.string.refreshing));

        // 设置下拉刷新文本
        ILoadingLayout endLabels = lv.getLoadingLayoutProxy(false, true);
        if(isLastPage)
        {
            endLabels.setPullLabel(getResources().getString(R.string.pull_up_load_more));
            endLabels.setReleaseLabel(getResources().getString(R.string.release_load_more));
            endLabels.setRefreshingLabel(getResources().getString(R.string.last_page));
        }else
        {
            endLabels.setPullLabel(getResources().getString(R.string.pull_up_load_more));
            endLabels.setReleaseLabel(getResources().getString(R.string.release_load_more));
            endLabels.setRefreshingLabel(getResources().getString(R.string.loading_more));
        }

    }

    /**
     * 设置更新时间
     *
     * @param refreshView
     */
    private void setUpdateTime(PullToRefreshBase refreshView) {
        String label = getStringDateNow();
        refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
    }


    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDateNow() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    protected void doSocket(){

        final GetBlogListRequest.GetBlogListRequestMessage.Builder requestMessage = GetBlogListRequest.GetBlogListRequestMessage.newBuilder();
//        requestMessage.setSession("8824a52b56454af8b46f610b1d068c60");
        requestMessage.setSession("50f489712bac4068980f1e5836515d6f");
        requestMessage.setPageIndex(pageNum);
        new Thread() {
            public void run() {
                SocketClient client;
                client = SocketClient.getInstance();
                client.send(SocketAppPacket.COMMAND_ID_Get_Blog_List, requestMessage.build().toByteArray());
            }
        }.start();

    }

    @Override
    public void onEventMainThread(SocketAppPacket eventPackage) {
        try {

            if (eventPackage.getCommandId() == SocketAppPacket.COMMAND_ID_Get_Blog_List) {
                GetBlogListResponse.GetBlogListResponseMessage responseMessage = GetBlogListResponse.GetBlogListResponseMessage.parseFrom(eventPackage.getCommandData());
                if (responseMessage.getErrorMsg().getErrorCode() == 0) {
                    if (getDataType == 1 || getDataType == 0) {
                        if (listBlog.size() > 0) {
                            listBlog.clear();
                        }
                        for (int i = 0; i < responseMessage.getBlogsList().size(); i++) {
                            Blog blog = new Blog();
                            blog.id = responseMessage.getBlogsList().get(i).getBlogId();
                            blog.address = responseMessage.getBlogsList().get(i).getAddress();
                            blog.author = responseMessage.getBlogsList().get(i).getAuthor();
                            blog.authorPhoto = responseMessage.getBlogsList().get(i).getAuthorPhoto();
                            blog.content = responseMessage.getBlogsList().get(i).getContent();
                            blog.commentCount = responseMessage.getBlogsList().get(i).getCommentCount();
                            blog.createDt = responseMessage.getBlogsList().get(i).getCreateDt();
                            blog.userId = responseMessage.getBlogsList().get(i).getUserId();
                            blog.likeCount = responseMessage.getBlogsList().get(i).getLikeCount();
                            blog.isLike = responseMessage.getBlogsList().get(i).getIsLike() == true ? 1 : 0;
                            List<Image> listImage = new ArrayList<>();
                            for (int j = 0; j < responseMessage.getBlogsList().get(i).getImagesList().size(); j++) {
                                Image image = new Image();
                                image.ImageUrl = responseMessage.getBlogsList().get(i).getImagesList().get(j);
                                listImage.add(image);
                            }
                            blog.listImage = listImage;
                            listBlog.add(blog);

                        }
                    }else if (getDataType == 2) {
                        for (int i = 0; i < responseMessage.getBlogsList().size(); i++) {
                            Blog blog = new Blog();
                            blog.id = responseMessage.getBlogsList().get(i).getBlogId();
                            blog.address = responseMessage.getBlogsList().get(i).getAddress();
                            blog.author = responseMessage.getBlogsList().get(i).getAuthor();
                            blog.authorPhoto = responseMessage.getBlogsList().get(i).getAuthorPhoto();
                            blog.content = responseMessage.getBlogsList().get(i).getContent();
                            blog.commentCount = responseMessage.getBlogsList().get(i).getCommentCount();
                            blog.createDt = responseMessage.getBlogsList().get(i).getCreateDt();
                            blog.userId = responseMessage.getBlogsList().get(i).getUserId();
                            blog.likeCount = responseMessage.getBlogsList().get(i).getLikeCount();
                            blog.isLike = responseMessage.getBlogsList().get(i).getIsLike() == true ? 1 : 0;
                            List<Image> listImage = new ArrayList<>();
                            for (int j = 0; j < responseMessage.getBlogsList().get(i).getImagesList().size(); j++) {
                                Image image = new Image();
                                image.ImageUrl = responseMessage.getBlogsList().get(i).getImagesList().get(j);
                                listImage.add(image);
                            }
                            blog.listImage = listImage;
                            listBlog.add(blog);
                        }
                    }

                    if (responseMessage.getIsLastPage()) {
                        isLastPage = true;
                    }
                    msgAdapter.setDatas(listBlog);

                }



            }
        }catch (InvalidProtocolBufferException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
