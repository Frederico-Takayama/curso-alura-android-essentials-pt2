package alura.com.br.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alura.com.br.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorDeIds);
        alunos.add(alunoCriado);
        contadorDeIds++;
    }

    public void edita(Aluno aluno){
        Aluno alunoEncontrado = null;
        for (Aluno a:
             alunos) {
            if(a.getId() == aluno.getId()){
                alunoEncontrado = a;
            }
        }
        if(alunoEncontrado != null){
            int posicaoDoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoAluno, aluno);
        }
    }

    public List<Aluno> todos() {
        return  new ArrayList<>(alunos);
    }
}
