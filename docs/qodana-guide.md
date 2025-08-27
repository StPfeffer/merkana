# Executar o Qodana Localmente

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Execução via Docker](#método-1-execução-via-docker-recomendado)
- [Execução via CLI](#método-2-via-cli-sem-docker)
- [Dicas](#dicas-úteis)
- [Interpretando os Resultados](#interpretando-os-resultados)
- [Integração com IDE](#integração-com-ide)
- [Limpeza](#limpeza)

## Pré-requisitos

- Docker instalado e em execução
- Projeto Java/Spring Boot configurado
- 4GB+ de RAM disponível (para projetos grandes)

## Método 1: Execução via Docker (Recomendado)

### Passo a Passo Básico

1. **Execute o scanner** no seu projeto:
```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  -p 8080:8080 \
  jetbrains/qodana-jvm
```

2. **Acesse o relatório**:
    - Abra no navegador: `http://localhost:8080`

### Opções Avançadas

- **Salvar relatório em JSON**:
```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  jetbrains/qodana-jvm --save-report
```

- **Ignorar problemas específicos**:
```bash
docker run --rm -it \
  -v $(pwd)/qodana.yaml:/data/project/qodana.yaml \
  jetbrains/qodana-jvm --fail-threshold=high
```

## Método 2: Via CLI (Sem Docker)

1. **Instale o Qodana CLI**:
```bash
curl -fsSL https://raw.githubusercontent.com/JetBrains/qodana-cli/main/install.sh | sh
```

2. **Execute a análise**:
```bash
qodana scan --show-report
```

## Dicas Úteis

- **Para projetos Maven**: Adicione `-e MAVEN_CONFIG=/data/project/.m2` para cache de dependências
- **Memória insuficiente?** Adicione `-e QODANA_MEM=4096` para aumentar para 4GB
- **Analisar apenas arquivos modificados**:
```bash
qodana diff --baseline=origin/main
```
- Os relatórios são salvos por padrão em `.qodana/results`.

## Interpretando os Resultados

- **Problemas críticos**: Marcados em vermelho (falhas de segurança, bugs graves)
- **Sugestões**: Marcadas em azul (melhorias de código)
- **Estatísticas**: Encontradas no arquivo `qodana/results/results.json`

## Integração com IDE

1. **Importe os resultados no IntelliJ IDEA**:
    - `File` > `Open` > Selecione o diretório `qodana/results`

2. **Visualize problemas diretamente no editor** com anotações inline

## Limpeza

Remova containers temporários:
```bash
docker container prune -f
``` 
