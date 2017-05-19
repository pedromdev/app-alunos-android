package com.example.pedromdev.appalunos;

import android.content.DialogInterface;
import android.content.Intent;
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
                intent.putExtra("matricula_aluno", aluno.getMatricula());
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

        switch (item.getItemId()) {
            case R.id.editar_aluno:
                Intent intent = new Intent(this, FormDadosAlunoActivity.class);
                intent.putExtra("matricula_aluno", alunoSelecionado.getMatricula());
                startActivity(intent);
                break;
            case R.id.excluir_aluno:
                excluirAluno();
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
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
