package com.sam_chordas.android.stockhawk.widget;

import android.content.Intent;
import android.database.Cursor;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;
import com.sam_chordas.android.stockhawk.ui.DetailActivity;

/**
 * Created by Gurpreet on 16/10/16.
 */

public class StockHawkWidgetRemoteViewsService extends RemoteViewsService {

    private final static String[] STOCK_COLUMNS = {
            QuoteColumns._ID,
            QuoteColumns.BIDPRICE,
            QuoteColumns.SYMBOL,
            QuoteColumns.CHANGE,
            QuoteColumns.PERCENT_CHANGE,
            QuoteColumns.ISUP
    };

    private final int INDEX_ID = 0;
    private final int INDEX_SYMBOL = 1;
    private final int INDEX_BIDPRICE = 2;
    private final int INDEX_PERCENT_CHANGE = 3;
    private final int INDEX_ISUP = 5;


    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {

        return new RemoteViewsFactory() {

            private Cursor data = null;

            @Override
            public void onCreate() {
            }

            @Override
            public void onDataSetChanged() {
                if (data != null)
                    data.close();
                final long identityToken = Binder.clearCallingIdentity();
                data = getContentResolver().query(
                        QuoteProvider.Quotes.CONTENT_URI,
                        STOCK_COLUMNS,
                        QuoteColumns.ISCURRENT + "=?",
                        new String[]{"1"},
                        null
                );
                Binder.restoreCallingIdentity(identityToken);
            }

            @Override
            public void onDestroy() {
                if (data != null) {
                    data.close();
                    data = null;
                }
            }

            @Override
            public int getCount() {
                return (data == null) ? 0 : data.getCount();
            }

            @Override
            public RemoteViews getViewAt(int position) {
                if (position == AdapterView.INVALID_POSITION ||
                        data == null || !data.moveToPosition(position)) {
                    return null;
                }
                RemoteViews views = new RemoteViews(getPackageName(), R.layout.list_item_quote);

                String stockSymbols, bidPrice;
                stockSymbols = data.getString(INDEX_SYMBOL);
                bidPrice = data.getString(INDEX_BIDPRICE);
                int isUp = data.getInt(INDEX_ISUP);
                views.setTextViewText(R.id.stock_symbol, stockSymbols);
                views.setTextViewText(R.id.bid_price, bidPrice);
                if (isUp == 1) {
                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
                } else {
                    views.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
                }
                views.setTextViewText(R.id.change, data.getString(INDEX_PERCENT_CHANGE));
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra(getString(R.string.symbol_label), stockSymbols);
                views.setOnClickFillInIntent(R.id.widget_list_item, intent);
                return views;
            }

            @Override
            public RemoteViews getLoadingView() {
                return new RemoteViews(getPackageName(), R.layout.list_item_quote);
            }

            @Override
            public int getViewTypeCount() {
                return 1;
            }

            @Override
            public long getItemId(int position) {
                if (data.moveToPosition(position))
                    return data.getLong(INDEX_ID);
                return position;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }
        };
    }
}