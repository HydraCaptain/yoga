package com.example.yogasehoga;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;

public class UploadYogaActivity extends AppCompatActivity {
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload_yoga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();

        YogaPose pose1 = new YogaPose(
                "Adho Mukha Svanasana",
                "https://i.ibb.co/DP7dtcMd/Adho-Mukha-Svanasana.jpg",
                "https://www.youtube.com/shorts/ILYV8GwnNYo",
                "From all fours, tuck your toes and lift your hips toward the ceiling, forming an inverted \"V\" shape.",
                "Keep your hands shoulder-width apart and feet hip-width apart.",
                "Press your heels toward the floor and relax your head between your arms.",
                "Stretches spine, strengthens arms and legs, calms the brain.",
                "Avoid with wrist or shoulder injuries.",
                Arrays.asList("15-27", "28-45"),
                Arrays.asList("Back", "Arm")
        );

        YogaPose pose2 = new YogaPose(
                "Ananda Balasana",
                "https://i.ibb.co/bRWhrjNw/Ananda-Balasana.jpg",
                "https://www.youtube.com/shorts/R_I-wpZQ_48",
                "Lie on your back, bend your knees toward your chest, and hold the outsides of your feet with your hands.",
                "Gently pull your knees toward the floor beside your torso.",
                "",
                "Stretches groins and spine, calms the brain, relieves lower back pain.",
                "Avoid with knee injuries.",
                Arrays.asList("5-14", "15-27", "28-45"),
                Arrays.asList("Back", "Knee", "Hips")
        );
        YogaPose pose3 = new YogaPose(
                "Ardha Matsyendrasana",
                "https://i.ibb.co/3ymtSyjS/Ardha-Matsyendrasana.jpg",
                "https://www.youtube.com/shorts/KcF-eZLLKpo",
                "Sit on the floor with both legs extended.\n" +
                        "\n" +
                        "Bend your right leg and place the right foot on the outside of the left thigh.\n" +
                        "\n" +
                        "Bend the left leg, bringing the left heel near the right hip. (If uncomfortable, keep the left leg extended.)",
                "Inhale, lift your left arm upward.\n" +
                        "\n" +
                        "Exhale, twist to the right, placing the left elbow outside the right knee.\n" +
                        "\n" +
                        "Place your right hand behind your back, fingers touching the floor for support.",
                "Keep the spine tall and gaze over the right shoulder.\n" +
                        "\n" +
                        "Hold for 30 seconds to 1 minute, breathing deeply.\n" +
                        "\n" +
                        "Exhale to release and repeat on the other side.",
                "Improves spinal flexibility and posture.\n" +
                        "\n" +
                        "Stimulates digestion and detoxifies internal organs.\n" +
                        "\n" +
                        "Tones abdominal and back muscles.\n" +
                        "\n" +
                        "Enhances lung capacity by opening the chest.\n" +
                        "\n" +
                        "Helps relieve mild back pain and stiffness.",
                "Avoid during pregnancy due to abdominal compression.\n" +
                        "\n" +
                        "Do not practice with spinal injuries or herniated discs.\n" +
                        "\n" +
                        "Skip or modify if you have knee or hip problems.\n" +
                        "\n" +
                        "Not recommended during menstruation by some traditions.\n" +
                        "\n" +
                        "Avoid with hernia, ulcers, or recent abdominal surgery.",
                Arrays.asList("15-27", "28-45"),
                Arrays.asList("Balance", "Knee", "Back")
        );
        YogaPose pose4 = new YogaPose(
                "Bhujangasana",
                "https://i.ibb.co/B28WXDMX/Bhujangasana.jpg",
                "https://www.youtube.com/shorts/F6V_3LUYp5U",
                "Lie on your stomach with hands under shoulders.",
                "Press into your hands to lift your chest, keeping elbows slightly bent and close to your body.",
                "",
                "Strengthens spine, stretches chest and lungs, relieves stress.",
                "Avoid with back or wrist injuries.",
                Arrays.asList("15-27", "28-45", "45+"),
                Arrays.asList("Back", "Chest", "Arm")
        );

        YogaPose pose5 = new YogaPose(
                "Bitilasana",
                "https://i.ibb.co/8L0vQFTQ/Bitilasana.jpg",
                "https://www.youtube.com/shorts/Y3vrNVj6N4U",
                "From all fours, inhale and arch your back, lifting your head and tailbone toward the ceiling.",
                "Exhale, return to a neutral spine.",
                "",
                "Improves spine flexibility and posture, aids digestion.",
                "Avoid if experiencing neck or back injuries.",
                Arrays.asList("5-14", "15-27"),
                Arrays.asList("Back")
        );

        YogaPose pose6 = new YogaPose(
                "Dandasana",
                "https://i.ibb.co/mrJgY9R6/Dandasana.jpg",
                "https://www.youtube.com/shorts/WesiSYdG2OI",
                "Sit with legs extended straight ahead, feet flexed.",
                "Place hands beside hips, pressing into the floor to lengthen the spine.",
                "",
                "Strengthens back, stretches shoulders and chest, improves posture.",
                "Use a folded blanket if hamstrings are tight.",
                Arrays.asList("28-45", "45+"),
                Arrays.asList("Back", "Chest")
        );
        YogaPose pose7 = new YogaPose(
                "Dhyana mudra",
                "https://i.ibb.co/RGZbwZ4z/Dhyana-mudra.jpg",
                "https://www.youtube.com/shorts/CmmbvLven2o",
                "Sit comfortably in a meditative posture (like Padmasana or Sukhasana).",
                "Place your hands on your lap, palms facing upwards.",
                "Rest the right hand over the left, and gently touch the tips of your thumbs together.",
                "Enhances concentration and mental clarity.\n" +
                        "\n" +
                        "Promotes calmness and reduces stress.\n" +
                        "\n" +
                        "Balances the flow of energy in the body.\n" +
                        "\n" +
                        "Helps deepen meditation and mindfulness.\n" +
                        "\n" +
                        "Stabilizes the mind and emotions.\n" +
                        "\n",
                "Ensure a comfortable seated posture to avoid strain.\n" +
                        "\n" +
                        "Avoid forcing hand placement; keep hands relaxed.\n" +
                        "\n" +
                        "Not suitable if you have wrist injuries or pain.\n" +
                        "\n" +
                        "Should be practiced in a calm, quiet environment.\n" +
                        "\n" +
                        "Avoid if it causes discomfort or numbness in fingers.\n" +
                        "\n",
                Arrays.asList("5-14", "15-27", "28-45", "45+"),
                Arrays.asList("Balance", "Mind")
        );
        YogaPose pose8 = new YogaPose(
                "Marjaryasana",
                "https://i.ibb.co/7JmRmbgW/Marjaryasana.jpg",
                "https://www.youtube.com/shorts/Y3vrNVj6N4U",
                "Begin on all fours with wrists under shoulders and knees under hips.",
                "Exhale, round your spine toward the ceiling, tucking your chin to your chest.",
                "Inhale, return to a neutral spine.",
                "Stretches back and neck, massages spine and organs.",
                "Caution with neck injuries.",
                Arrays.asList("5-14", "15-27"),
                Arrays.asList("Back")
        );

        YogaPose pose9 = new YogaPose(
                "Paschimottanasana",
                "https://i.ibb.co/7NtSZS3j/Paschimottanasana.jpg",
                "https://www.youtube.com/shorts/QQYxuGFhWSw",
                "Sit with legs extended straight in front.",
                "Inhale, lengthen spine; exhale, fold forward, reaching for your feet or shins.",
                "",
                "Calms brain, stretches spine and hamstrings.",
                "Avoid if you have asthma or diarrhea.",
                Arrays.asList("15-27", "28-45"),
                Arrays.asList("Back")
        );

        YogaPose pose10 = new YogaPose(
                "Savasana",
                "https://i.ibb.co/0RQdJDrq/Savasana.jpg",
                "https://www.youtube.com/shorts/ScO5yREuZ68",
                "Lie flat on your back with legs extended and arms at your sides.",
                "Close your eyes and breathe naturally.",
                "",
                "Relaxes the body, reduces stress, headache, and fatigue.",
                "Use support under knees for back issues.",
                Arrays.asList("28-45", "45+"),
                Arrays.asList("Back")
        );

        YogaPose pose11 = new YogaPose(
                "Sukhasana",
                "https://i.ibb.co/zH6574pY/Sukhasana.jpg",
                "https://www.youtube.com/shorts/uJFdJehyr4w",
                "Sit cross-legged with a straight spine, hands resting on knees.",
                "Close your eyes and focus on your breath.",
                "",
                "Calms brain, strengthens back, opens hips.",
                "Use support if discomfort in knees or hips.",
                Arrays.asList("5-14", "15-27"),
                Arrays.asList("Back", "Hips", "Knee")
        );

        YogaPose pose12 = new YogaPose(
                "Tadasana",
                "https://i.ibb.co/rGMwgynr/tadasan.jpg",
                "https://www.youtube.com/shorts/E1xym-F_B84",
                "Stand with your feet together, grounding evenly through both feet.",
                "Engage thighs, lift chest, let arms hang naturally.",
                "Lengthen your spine and reach through the crown of your head.",
                "Improves posture, strengthens legs, increases awareness.",
                "Avoid if low blood pressure or headache.",
                Arrays.asList("5-14", "15-27"),
                Arrays.asList("Balance", "Ankle", "Back")
        );

        YogaPose pose13 = new YogaPose(
                "Urdhva Mukha Svanasana",
                "https://i.ibb.co/nsV75r0N/Urdhva-Mukha-Svanasana.jpg",
                "https://www.youtube.com/shorts/0xYhkI_yYU8",
                "Lie face down, press into hands to lift torso and legs a few inches off floor.",
                "Keep arms straight and shoulders over wrists, opening chest forward.",
                "",
                "Strengthens spine, arms, and wrists. Stretches chest and abdomen.",
                "Avoid with back injuries or carpal tunnel.",
                Arrays.asList("15-27", "28-45"),
                Arrays.asList("Back", "Chest", "Arm")
        );

        YogaPose pose14 = new YogaPose(
                "Vrikshasana",
                "https://i.ibb.co/27YgnrBT/Vrikshasana.jpg",
                "https://www.youtube.com/shorts/T1oiFP220zo",
                "Stand tall, shift weight onto one foot, place opposite foot on thigh or calf.",
                "Bring palms together at the chest or overhead.",
                "",
                "Improves balance, strengthens legs, stretches groins and chest.",
                "Avoid placing foot on knee.",
                Arrays.asList("5-14", "15-27"),
                Arrays.asList("Balance", "Knee", "Ankle")
        );

        uploadPose(pose1);
        uploadPose(pose2);
        uploadPose(pose3);
        uploadPose(pose4);
        uploadPose(pose5);
        uploadPose(pose6);
        uploadPose(pose7);
        uploadPose(pose8);
        uploadPose(pose9);
        uploadPose(pose10);
        uploadPose(pose11);
        uploadPose(pose12);
        uploadPose(pose13);
        uploadPose(pose14);

    }
    private void uploadPose(YogaPose pose) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("YogaPose")
                .document(pose.YogaName.replace(" ", "_")) // use YogaName as document ID
                .set(pose)
                .addOnSuccessListener(aVoid ->
                        Log.d("Firebase", pose.YogaName + " uploaded successfully"))
                .addOnFailureListener(e ->
                        Log.e("Firebase", "Failed to upload " + pose.YogaName, e));
    }
}