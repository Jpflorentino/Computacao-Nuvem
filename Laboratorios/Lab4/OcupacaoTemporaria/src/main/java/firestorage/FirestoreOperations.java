package firestorage;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FirestoreOperations {

    Firestore firestore = null;

    public FirestoreOperations(Firestore firestore) {
        this.firestore = firestore;
    }

    public OcupacaoTemporaria convertLineToObject(String line) throws ParseException {
        String[] cols = line.split(",");
        OcupacaoTemporaria ocup = new OcupacaoTemporaria();
        ocup.ID = Integer.parseInt(cols[0]);
        ocup.location = new Localizacao();
        ocup.location.point = new GeoPoint(Double.parseDouble(cols[1]), Double.parseDouble(cols[2]));
        ocup.location.coord = new Coordenadas();
        ocup.location.coord.X = Double.parseDouble(cols[1]);
        ocup.location.coord.Y = Double.parseDouble(cols[2]);
        ocup.location.freguesia = cols[3];
        ocup.location.local = cols[4];
        ocup.event = new Evento();
        ocup.event.evtID = Integer.parseInt(cols[5]);
        ocup.event.nome = cols[6];
        ocup.event.tipo = cols[7];
        ocup.event.details = new HashMap<String, String>();
        if (!cols[8].isEmpty()) ocup.event.details.put("Participantes", cols[8]);
        if (!cols[9].isEmpty()) ocup.event.details.put("Custo", cols[9]);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        ocup.event.dtInicio = formatter.parse(cols[10]);
        ocup.event.dtFinal = formatter.parse(cols[11]);
        ocup.event.licenciamento = new Licenciamento();
        ocup.event.licenciamento.code = cols[12];
        ocup.event.licenciamento.dtLicenc = formatter.parse(cols[13]);
        return ocup;
    }

    public void insertDocuments(String pathnameCSV, Firestore db, String collectionName) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(pathnameCSV));
        CollectionReference colRef = db.collection(collectionName);
        String line;
        while ((line = reader.readLine()) != null) {
            OcupacaoTemporaria ocup = convertLineToObject(line);
            DocumentReference docRef = colRef.document(ocup.ID + "");
            ApiFuture<WriteResult> result = docRef.set(ocup);
        }
        System.out.println("Documento inserido com sucesso!!");
    }

    public void showDocumentById(Firestore db, String collectionName, String documentId) throws Exception {
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.exists()) {
            System.out.println("Document Data: " + document.getData());
        } else {
            System.out.println("No such document!");
        }
    }

    public void deleteContent(Firestore db, String collectionName, String documentId, String fieldName) throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection(collectionName).document(documentId);
        Map<String, Object> updates = new HashMap<>();
        updates.put(fieldName, FieldValue.delete());
        ApiFuture<WriteResult> writeResult = docRef.update(updates);
        System.out.println("Update time: " + writeResult.get());
    }

    public void simpleInterrogation(Firestore db, String collectionName, String freguesia) throws ExecutionException, InterruptedException {
        CollectionReference docRef = db.collection(collectionName);
        FieldPath fp = FieldPath.of("location", "freguesia");
        Query query = docRef.whereEqualTo(fp, freguesia);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId());
        }
    }

    public void composeInterrogation(Firestore db, String collectionName, String documentId, String freguesia, String tipoEvento) throws ExecutionException, InterruptedException {
        CollectionReference docRef = db.collection(collectionName);
        FieldPath fpathFreguesia = FieldPath.of("location", "freguesia");
        FieldPath fpathEvento = FieldPath.of("event", "tipo");
        Query query = docRef.whereGreaterThan("ID", Integer.parseInt(documentId)) //Maior que um determinado id
                .whereEqualTo(fpathFreguesia, freguesia) //Igual a uma determinada freguesia
                .whereEqualTo(fpathEvento, tipoEvento);// Igual a um evento
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
            System.out.println(document.getId() + " : Doc: " + document.getData());
        }
    }
}
