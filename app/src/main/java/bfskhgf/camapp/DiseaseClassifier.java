package bfskhgf.camapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class DiseaseClassifier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_classifier);


       TensorFlowInferenceInterface tensorflow = new TensorFlowInferenceInterface();
       tensorflow.initializeTensorFlow(getAssets(), "file:///android_asset/model.pb");


// loading new input
       tensorflow.fillNodeFloat("input:0", INPUT_SHAPE, input);

// running inference for given input and reading output
       String outputNode = "output:0";
       String[] outputNodes = {outputNode};
       tensorflow.runInference(outputNodes);
       tensorflow.readNodeFloat(outputNode, output);
  }
}
