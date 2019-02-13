package com.bawie.shopmkk.di.Contart;

import com.bawie.shopmkk.bean.JsonBean;

public interface IContart {

        public interface IGouView{

            public void ShowData(JsonBean jsonBean);

        }

        public interface IGouPersenter<IGouView>{

            public void attach(IGouView iGouView);

            public void deatch(IGouView iGouView);

            public void requestdata();

        }

        public interface IGouModel{
            public void ContionData(OnCallBack onCallBack);

            public interface OnCallBack{
                public void OnCallBack(JsonBean jsonBean);
            };
        }
}
