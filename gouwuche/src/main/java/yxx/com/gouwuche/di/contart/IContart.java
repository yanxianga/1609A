package yxx.com.gouwuche.di.contart;

public interface IContart {

    public interface IContartView{

        public void ShowData(String requestData);
    }

    public interface IPersenter<IContartView>{

        public void attach(IContartView iContartView);

        public void deatch(IContartView iContartView);

        public void requestData();
    }

    public interface IMode{
        public void ContainData(OnCallBack onCallBack);

        public interface OnCallBack{
            public void OnCallBack(String requestData);
        }
    }
}
