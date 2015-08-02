#!/usr/bin/env groovy

convert_map = [ 
	"NC_000001.11" : "1"
	,"NC_000002.12" : "2"
	,"NC_000003.12" : "3"
	,"NC_000004.12" : "4"
	,"NC_000005.10" : "5"
	,"NC_000006.12" : "6"
	,"NC_000007.14" : "7"
	,"NC_000008.11" : "8"
	,"NC_000009.12" : "9"
	,"NC_000010.11" : "10"
	,"NC_000011.10" : "11"
	,"NC_000012.12" : "12"
	,"NC_000013.11" : "13"
	,"NC_000014.9" : "14"
	,"NC_000015.10" : "15"
	,"NC_000016.10" : "16"
	,"NC_000017.11" : "17"
	,"NC_000018.10" : "18"
	,"NC_000019.10" : "19"
	,"NC_000020.11" : "20"
	,"NC_000021.9" : "21"
	,"NC_000022.11" : "22"
	,"NC_000023.11" : "X"
	,"NC_000024.10" : "Y"
	]


def fis = new FileInputStream("GenBankGff3/ref_GRCh38.p2_top_level.gff3")
def fos = new FileOutputStream("GenBankGff3/GenbankOutput.fa")

//>NC_000017.11 Homo sapiens chromosome 17, GRCh38.p2 Primary Assembly
fis.eachLine(){ line -> 
   if(line.startsWith(">NC")){
	   String[] lines = line.split(" ")
	   String key = lines[0].substring(1)
	   String replaced = "chr"+convert_map[key]
	   replaced = ">"+replaced + " " +  (lines as List).subList(1,lines.size()).join(" ") 
       fos << replaced  
   }
   else{
	   fos << line
   }
   fos << "\n"
}

fos.close()
fis.close()

