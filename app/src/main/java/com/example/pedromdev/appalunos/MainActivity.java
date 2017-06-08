package com.example.pedromdev.appalunos;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pedromdev.appalunos.modelo.bean.Aluno;
import com.example.pedromdev.appalunos.modelo.dao.AlunoDAO;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Aluno> listaAlunos;

    private ArrayAdapter<Aluno> adapter;

    private int adapterLayout = android.R.layout.simple_list_item_1;

    private ListView listaViewAlunos;

    private Aluno alunoSelecionado = null;

    private final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaViewAlunos = (ListView) findViewById(R.id.lista_alunos);

        registerForContextMenu(listaViewAlunos);

        listaViewAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, InfoAlunoActivity.class);
                intent.putExtra("ALUNO", aluno);
                startActivity(intent);
            }
        });

        listaViewAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                alunoSelecionado = (Aluno) parent.getItemAtPosition(position);
                Log.i(TAG, "Aluno selecionado ListView.longClick(): " + alunoSelecionado.getNome());
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = this.getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.novo_aluno:
                Intent intent = new Intent(this, FormDadosAlunoActivity.class);
                startActivity(intent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_acoes_aluno, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent;

        switch (item.getItemId()) {
            case R.id.editar_aluno:
                Log.i(TAG, "Editando os dados do aluno");
                intent = new Intent(this, FormDadosAlunoActivity.class);
                intent.putExtra("ALUNO", alunoSelecionado);
                startActivity(intent);
                break;
            case R.id.excluir_aluno:
                Log.i(TAG, "Excluindo os dados do aluno");
                excluirAluno();
                break;
            case R.id.ligar_para_aluno:
                Log.i(TAG, "Ligando para o aluno");
                intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
                startActivity(intent);
                break;
            case R.id.enviar_sms_aluno:
                Log.i(TAG, "Enviando SMS para o aluno");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
                intent.putExtra("sms_body", "Mensagem de boas vindas");
                startActivity(intent);
                break;
            case R.id.achar_no_mapa:
                Log.i(TAG, "Buscando endereço do aluno");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSelecionado.getEndereco()));
                startActivity(intent);
                break;
            case R.id.navegar_no_site:
                Log.i(TAG, "Entrando no site do aluno");
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http:" + alunoSelecionado.getSite()));
                startActivity(intent);
                break;
            case R.id.enviar_email:
                Log.i(TAG, "Enviando e-mail para o aluno");
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {alunoSelecionado.getEmail()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Falando sobre o curso");
                intent.putExtra(Intent.EXTRA_TEXT, "O curso foi muito legal");
                startActivity(intent);
                break;
            default:
                Log.i(TAG, "Nenhuma açao realizada");
                break;
        }
        return super.onContextItemSelected(item);
    }

    private boolean isGranted(String permission) {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    private void carregarLista() {
        AlunoDAO dao = new AlunoDAO(this);
        listaAlunos = dao.listar();
        dao.close();

        adapter = new ArrayAdapter<Aluno>(this, adapterLayout, listaAlunos);
        listaViewAlunos.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void excluirAluno() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja excluir o(a) aluno(a) " + alunoSelecionado.getNome() + "?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlunoDAO dao = new AlunoDAO(MainActivity.this);
                dao.deletar(alunoSelecionado);
                dao.close();
                carregarLista();
                alunoSelecionado = null;
            }
        });

        builder.setNegativeButton("Não", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("Confirmar exclusão");
        alertDialog.show();
    }
}
