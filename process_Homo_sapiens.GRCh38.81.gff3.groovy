#!/usr/bin/env groovy

lookup_list = [ 
 "1"
, "2"
, "3"
, "4"
, "5"
, "6"
, "7"
, "8"
, "9"
, "10"
, "11"
, "12"
, "13"
, "14"
, "15"
, "16"
, "17"
, "18"
, "19"
, "20"
, "21"
, "22"
, "X"
, "Y"
	]


def fis = new FileInputStream("GRCH38.81-release/Homo_sapiens.GRCh38.81.gff3")
def fos = new FileOutputStream("GRCH38.81-release/Output.gff3")

//>NC_000017.11 Homo sapiens chromosome 17, GRCh38.p2 Primary Assembly
//1       havana  gene    11869   14409   .       +       .       ID=gene:ENSG00000223972;Name=DDX11L1;biotype=transcribed_unprocessed_pseudogene;description=DEAD/H (Asp-Glu-Ala-Asp/His) box helicase 11 like 1 [Source:HGNC Symbol%3BAcc:HGNC:37102];gene_id=ENSG00000223972;havana_gene=OTTHUMG00000000961;havana_version=2;logic_name=havana;version=5
// 
fis.eachLine(){ line -> 
   String[] lines = line.split("\t")
   if(lookup_list.contains(lines[0])){
	   String key = lines[0].substring(1)
	   String replaced = "chr"+lines[0]
	   replaced = replaced + "\t" +  (lines as List).subList(1,lines.size()).join("\t") 
       fos << replaced  
   }
   else{
	   fos << line
   }
   fos << "\n"
}

fos.close()
fis.close()

