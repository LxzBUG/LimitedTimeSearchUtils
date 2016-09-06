# LimitedTimeSearchUtils
**How to use?**  

``` 
etSearch.addTextChangedListener(textWatcher);
```

```
public TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(final CharSequence s, int start, int before, int count) {
     
                RealTimeSearchUtil.sendRealTimeSearchMessage(s.toString(),500, new RealTimeSearchUtil.RealTimeSearchUtilLisetener() {
                    @Override
                    public void handleUIMessage() {
                      //发起请求
                    }
                });
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

```  
