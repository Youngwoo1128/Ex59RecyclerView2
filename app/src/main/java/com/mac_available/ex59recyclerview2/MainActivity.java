package com.mac_available.ex59recyclerview2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Github
    // 이제 pull

    ArrayList<Item> items = new ArrayList<>();
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //대량의 데이터 추가 [실무에서는 DB or Network 서버에서 데이터를 읽어옴]
        items.add((new Item("루피", "해적단 선장", R.drawable.img01, R.drawable.img02)));
        items.add(new Item("조로", "해적단 부선장", R.drawable.ch_zoro, R.drawable.img03));
        items.add(new Item("나미", "해적단 항해사", R.drawable.ch_nami, R.drawable.img04));
        items.add(new Item("우솝", "해적단 저격수", R.drawable.ch_usoup, R.drawable.img05));
        items.add(new Item("상디", "해적단 요리사", R.drawable.ch_sandi, R.drawable.moana));
        items.add(new Item("쵸파", "해적단 의사", R.drawable.ch_chopa, R.drawable.winter));

        recyclerView = findViewById(R.id.recycler);
        adapter = new MyAdapter(this, items);
        recyclerView.setAdapter(adapter);

        //리사이클러뷰눈 리스트 뷰와 다르게 아이템 클릭 리스너가 없음
        //아답터에서 itemView에 직접 클릭리스너 설정해 주어야 함.
    }

    public void clickAdd(View view) {
        //새로운 아이템 추가[리사이클러뷰에 하는 것이 아니라 ArrayList 에 추가]
//        items.add( new Item("NEW","MESSAGE", R.drawable.ch_sandi, R.drawable.img10) );
//        //아답터에게 새로운 데이터가 추가되었다고 공지!!!! - 자동화면갱신
//        //adapter.notifyDataSetChanged();//1개 추가되었지만 전체를 다시 갱신함(비효율적)
//        //새로운 아이템 1개가 추가되었다고 공지!!!
//        adapter.notifyItemInserted(items.size()-1);
//        //리사이클러뷰의 스크롤위치를 조정
//        recyclerView.scrollToPosition(items.size()-1);

        //보통 새로 추가된 아이템은 첫번째(index:0)로 추가되는 경우가 더 많음[최신순]
        items.add(0, new Item("NEW", "MESSSAGE", R.drawable.ch_sandi, R.drawable.img01));
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }

    public void clickDel(View view) {
        if (items.size()==0)return;

        //리사이클러뷰의 아이템뷰를 삭제하는 것이 아니라. ArrayList의 요소제거
        items.remove(0);
        adapter.notifyItemRemoved(0);
    }

    public void clickLinear(View view) {
        //리사이클러뷰의 배치관리자(LayoutManager)변경하기
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    public void clickGrid(View view) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);//2칸짜리 격자배치
        recyclerView.setLayoutManager(layoutManager);
    }
}
