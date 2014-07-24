package com.kevinotoole.usmcaircraft;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by kevinotoole on 7/23/14.
 */
public class AircraftWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int n = appWidgetIds.length;

        for (int i = 0; i<n; i++){
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MyActivity.class);

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            remoteViews.setOnClickPendingIntent(R.id.widgetImage, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    public void onDeleted(Context context, int[] appWidgetIds) {

    }


}
