package co.ab180.exabr.presentation.activity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import co.ab180.airbridge.Airbridge;
import co.ab180.airbridge.AirbridgeCallback;
import co.ab180.airbridge.event.StandardEventCategory;
import co.ab180.airbridge.event.model.Product;
import co.ab180.airbridge.event.model.SemanticAttributes;
import co.ab180.exabr.R;
import co.ab180.exabr.databinding.ActivityMainBinding;
import co.ab180.exabr.presentation.dialog.MessageDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initButton();
    }

    private void initButton() {

        binding.btnTrackEvent.setOnClickListener(view -> {
            Product lipstick = new Product();
            lipstick.setId("lipstick_red");
            lipstick.setName("Lipstick - Ruby Red");
            lipstick.setQuantity(1);
            lipstick.setCurrency("USD");
            lipstick.setPrice(12.99);
            lipstick.setPosition(0);

            Product foundation = new Product();
            foundation.setId("foundation_light");
            foundation.setName("Foundation - Light Shade");
            foundation.setQuantity(1);
            foundation.setCurrency("USD");
            foundation.setPrice(24.99);
            foundation.setPosition(1);

            SemanticAttributes semanticAttributes = new SemanticAttributes();
            semanticAttributes.setProducts(Arrays.asList(lipstick, foundation));
            semanticAttributes.setCurrency("USD");
            semanticAttributes.setTotalValue(37.98);
            semanticAttributes.setInAppPurchased(true);

            Airbridge.trackEvent(
                    StandardEventCategory.ORDER_COMPLETED,
                    null,
                    null,
                    null,
                    null,
                    semanticAttributes
            );

            Toast.makeText(this, R.string.button_track_event, Toast.LENGTH_SHORT).show();
        });

        binding.btnHybridTrackEvent.setOnClickListener(view -> {
            startActivity(WebviewActivity.newIntent(this));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Airbridge.getDeeplink(
                getIntent(),
                new AirbridgeCallback<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // Process deeplink data
                        MessageDialog.show(
                                getSupportFragmentManager(),
                                Color.BLUE,
                                "getDeeplink|onSuccess",
                                uri.toString()
                        );
                    }

                    @Override
                    public void onFailure(@NonNull Throwable throwable) {
                        // Error
                        MessageDialog.show(
                                getSupportFragmentManager(),
                                Color.RED,
                                "getDeeplink|onFailure",
                                throwable.getMessage()
                        );
                    }

                    @Override
                    public void onComplete() {
                        // After process deeplink data
                    }
                }
        );
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}
