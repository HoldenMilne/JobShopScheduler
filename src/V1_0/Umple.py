# Run this file to compile any Umple files (.ump)

import sys,os,re
import subprocess
def Print(valuesDict,*args,**kwargs):
    if valuesDict['--debug']:
        print(*args,**kwargs)
def LoadIFregex(fileName):
    try:
        file = open(fileName,"r")
        print(file)
    except:
        return []
    lines =  file.readlines()
    r_lines = []
    for l in lines:
        r_lines.append(re.compile(l))
    return r_lines
def AddAllIgnoreFiles(values):
    ifRegex = LoadIFregex(values['-if'])
    matches = []
    dir = "Java/"
    if values['-d'] is not None:
        dir = values['-d']
    for root, dirs, files in os.walk(dir):
        path = root.split(os.sep)

        for r in ifRegex:
            if r.match(root):
                for f in files:
                    matches.append(f)
            if r.match(path[-1]):
                for f in files:
                    matches.append(f)
        for f in files:
            file = root+"/"+f
            for r in ifRegex:
                if r.match(file):
                    matches.append(file)
                elif "/" in file and r.match(file[file.rindex('/') + 1:]):
                    matches.append(file)

    values['-i']=values['-i']+matches
    return values;

def PrintHelp():
    print("================================ HELP ================================")
    print("  Use this to compile specific umple files automatically.  Default\n"
          "  is to compile a specific file, passes by ./Umple.py <file>.  The\n"
          "  following arguments can be used to change the behaviour.\n\n"
          "  Caution: Compiling umple files will replace code in the class\n"
          "  files of the same name, unless written in the umple file.  Hence\n"
          "  recursive compiling is not recommended unless you are absolutely\n"
          "  certain.\n\n"
          "\t-h,--help:\t\tDisplay this help text.\n\n"
          "\t-a,--args:\t\tSpecify the arguments to pass to umple.\n\n"
          "\t-f,--file:\t\tSpecify the file to compile.  Behaves\n\t\t\t\texactly the same as Umple.py <file>\n\t\t\t\t(default behaviour).\n\n"
          "\t-d,--directory <dir>:\tSet the directory to look in.\n\n"
          "\t-r,--recursive: \tIf -d is used, traverse recursively.\n\n"
          "\t-i,--ignore: \t\tExplicit files to ignore.  Use full \n\t\t\t\trelative paths.  Does not support regex.\n\n"
          "\t-if,--ignoreFile:\tSpecify a file that contains regular\n\t\t\t\texpressions of files to ignore.")


def ManualParseList(arg):
    print(arg)
    if arg[0]=='[' and arg[-1]==']':
        vals = arg[1:-1]
        print(vals)
        return vals.split(',')
    elif ',' in arg:
        return arg.split(',')
    else: return [arg];



def Parse(a, arg):
    if arg == "-i" or arg == '-a':
        return ManualParseList(a)
    else:
        return a


def procArgs(args):
    singles = ['-r', '--recursive','-h','--help','--debug','-y']
    # maps singular values to a fixed unit
    singDict = {'--recursive': 'r', '-r': '-r','-h':'-h','--help':'-h','--debug':'--debug', '-y': '-y'}

    paired = ['-d', '-D', '--directory', '-i', '--ignore'
        , '-if', '--ignorefile','-f','--file','-a','--args']
    # maps singular values to a fixed unit
    pairDict = {'-d': '-d', '-D': '-d', '--directory': '-d',
                '-i': '-i', '--ignore': '-i',
                '-if': '-if', '--ignorefile': '-if'
                ,'-f': '-f', '--file': '-f'
                ,'-a':'-a','--args':'-a'}

    values = {'-a': [],'-r': False, '-f':None,'-d': None, '-h': False, '-i': [], '-if': "",'--debug':False,'-y':False}
    get = False
    lastArg = None
    if len(args)==2 and args[1][0]!='-':
        values['-f'] = args[1]
    else:
        for a in args[1:]:
            A=a.lower()
            if A in singles:
                if get:
                    print(lastArg, "was passed without value.")
                    return None
                values[singDict[A]] = True;
            elif A in paired:
                if get:
                    print(lastArg, "was passed without value.")
                    return None
                lastArg = A
                get=True;
            else:
                if get and lastArg is not None:
                    arg = pairDict[lastArg]
                    values[arg] = Parse(a, arg);
                    lastArg = None
                    get=False
                else:
                    print("Unkown argument",a,"was passed")
                    print(lastArg)
                    return None;

    if values['-if']:
        values = AddAllIgnoreFiles(values)
    if values['-d'] is not None and values['-d'][-1]!="/":
        values['-d']+="/"
    if values['-f'] is not None and values['-f'][0:2]!="./" and values['-f'][0:1]!="/":
        values['-f'] = "./"+values['-f']
    return values


args = sys.argv
valuesDict = procArgs(args)

if valuesDict is None or valuesDict['-h']:
    PrintHelp()
    exit()
if valuesDict['-f'] is None and valuesDict['-d'] is None:
    print("Error: No file, nor directory was passed.")
    PrintHelp()
    exit()

if valuesDict['-f'] is not None and valuesDict['-d']  is not None:
    if not valuesDict['-f'].startswith(valuesDict['-d']):
        print("Both a file (-f) and a directory (-d) were specified.")
        resp = -1
        file = None
        while (resp<0 or resp>2):
            try:
                resp = int(input("Would you like to compile the file at "+
                         valuesDict['-d']+valuesDict['-f']+"\nor just use the file ./"+
                         valuesDict['-f']+"?\n\t[0] - "+valuesDict['-d']+valuesDict['-f']+"\n\t"+
                            "[1] - just "+valuesDict['-f']+"\n\t[2] - Neither (cancel)\n"))
                if resp == 0:
                    file = valuesDict['-d']+"/"+valuesDict['-f']
                elif resp == 1:

                    file = valuesDict['-f']
                elif resp==2:
                    break

            except:
                resp = -1
            print("\nInput not understood.  Try again.")

        if file is None:
            exit()

        valuesDict["-f"] == file
    else:
        valuesDict['-f']=valuesDict['-f'][len(valuesDict['-d']):]
Print(valuesDict,valuesDict)
if valuesDict['-d'] is None:
    valuesDict['-d'] = "./"

if valuesDict['-f'] is not None:
    file = valuesDict['-d'] + valuesDict['-f']
    umpleArgs = valuesDict['-a']
    if file not in valuesDict['-i'] and valuesDict['-f'] not in valuesDict['-i']:
        if len(umpleArgs) == 0:
            subprocess.call(['java', '-jar', '../../umple.jar',file])
        else:
            subprocess.call(['java', '-jar', '../../umple.jar']+umpleArgs+[file])
    else:
        print(file, "was ignored.")
else:
    FILES = []
    for root, dirs, files in os.walk(valuesDict["-d"]):
        for f in files:
            file = root+"/"+f
            if file.endswith('.ump'):
                if file not in valuesDict['-i'] and valuesDict['-f'] not in valuesDict['-i']:
                    FILES.append(file)
                else:
                    print(file, "was ignored.")
        if not valuesDict['-r']:
            break
    if len(FILES)==0:
        print("No files were found!")
        exit()
    if not valuesDict['-y']:
        resp = None
        stack = []
        while resp is None:

            print("The following files will be compiled.  Is this okay?\n")
            i = 0
            x = 0
            for f in FILES:
                print(str(i)+":",f,end=", \t")
                i+=1
                x=i%3
                if x==0:
                    print()

            if x != 0:
                print()
            resp = input("Type y, yes to compile or enter the file name or number you want to remove.  \nUse u or undo to undo.\nUse c or cancel to quit.\n")
            if resp in FILES:
                text = FILES.pop(FILES.index(resp))
                print(text,"was removed!")
                stack.append(text)
            elif resp.lower() == "y" or resp.lower()=="yes":
                break
            elif resp.lower() == 'u' or resp.lower() == 'undo':
                if len(stack) > 0:
                    text = stack.pop()
                    FILES.append(text)
                    print(text,"was added back")
                else:
                    print("Can't undo, nothing to return...")
            elif resp.lower()=='c' or resp.lower()=='cancel':
                print("Exiting") # maybe confirm?
                exit();
            else:
                success = False
                for f in FILES:
                    if f.endswith(resp):
                        text = FILES.pop(FILES.index(resp))
                        print(text,"was removed!")
                        stack.append(text)
                        success = True

                if not success:
                    try:
                        X = int(resp)
                        if X<len(FILES) and X>=0:
                            text = FILES.pop(X)
                            print(text,"was removed!")
                            stack.append(text)
                            success = True
                        else:
                            print("That index is not in the range")
                    except:
                        print("That file was not found. Try again.")
            resp = None
    umpleArgs = valuesDict['-a']
    for file in FILES:
        if file not in valuesDict['-i'] and valuesDict['-f'] not in valuesDict['-i']:
            if len(umpleArgs) == 0:
                subprocess.call(['java', '-jar', '../../umple.jar',file])
            else:
                subprocess.call(['java', '-jar', '../../umple.jar']+umpleArgs+[file])
        else:
            print(file, "was ignored.")

