package com.example.pedromdev.appalunos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pedromdev.appalunos.helper.FormularioHelper;
import com.example.pedromdev.appalunos.modelo.bean.Aluno;
import com.example.pedromdev.appalunos.modelo.dao.AlunoDAO;
import com.example.pedromdev.appalunos.validator.AlunoValidator;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pedromdev on 17/05/17.
 */

public class FormDadosAlunoActivity extends AppCompatActivity {

    private Button btnSalvar;

    private FormularioHelper helper;

    private AlunoDAO dao;

    private Aluno alunoSelecionado = null;

    private String localArquivo;

    private String mCurrentPhotoPath;

    private static final int FAZER_FOTO = 123;

    private static final String TAG = "FORMDADOSALUNO_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_dados_aluno);

        helper = new FormularioHelper(this);
        dao = new AlunoDAO(this);
        btnSalvar = (Button) findViewById(R.id.btnCadastrar);

        alunoSelecionado = (Aluno) getIntent().getSerializableExtra("ALUNO");

        if (alunoSelecionado != null) {
            helper.setDadosDoAluno(alunoSelecionado);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlunoValidator validator = new AlunoValidator(FormDadosAlunoActivity.this);

                if (!validator.isValid()) {
                    String str = "Alguns campos estao invalidos:";
                    for (String mensagem : validator.getMessages()) {
                        str += "\n - " + mensagem;
                    }
                    Toast.makeText(FormDadosAlunoActivity.this, str, Toast.LENGTH_LONG).show();
                    return;
                }

                Aluno aluno = helper.getAluno();

                if (alunoSelecionado == null) {
                    dao.cadastrar(aluno);
                } else {
                    dao.atualizar(aluno);
                }
                dao.close();

                Toast.makeText(
                    FormDadosAlunoActivity.this,
                    "Aluno cadastrado com sucesso",
                    Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });

        helper.getFoto().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i(TAG, "Abrindo camera");
                    File file = createImageFile();
                    Uri localFoto = FileProvider.getUriForFile(
                        FormDadosAlunoActivity.this,
                        getApplicationContext().getPackageName() + ".provider",
                        file
                    );
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    camera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);
                    startActivityForResult(camera, FAZER_FOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_voltar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_voltar:
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FAZER_FOTO:
                Log.i(TAG, "Resultado da camera");
                fazerFoto(resultCode);
                break;
        }
    }

    private void fazerFoto(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            helper.carregaFoto(localArquivo);
        } else {
            localArquivo = null;
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.i(TAG, "Timestamp: " + timeStamp);
        String imageFileName = "JPEG_" + timeStamp;
        File storageDir = new File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
            "Camera"
        );
        File image = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",         /* suffix */
            storageDir      /* directory */
        );

        Log.i(TAG, "Caminho da imagem: " + image.getAbsolutePath());

        // Save a file: path for use with ACTION_VIEW intents
        localArquivo = image.getAbsolutePath();
        return image;
    }
}
