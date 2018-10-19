import os
import re
import codecs

# textfile = '/Users/nmiran/Desktop/nadav.txt'
textfile = '/Users/nmiran/Desktop/BenGurion.txt'
folderpath = '/Users/nmiran/Desktop/textfiles'

excelFilePath = '/Users/nmiran/Desktop/nadav.xlsx'
ignoreWords = [' a ', ' about ', ' above ', ' after ', ' again ', ' against ', ' all ', ' am ', ' an ', ' and ',
               ' any ',
               ' are ', ' aren\'t ', ' as ', ' at ', ' be ', ' because ', ' been ', ' before ', ' being ', ' below ',
               ' between ', ' both ', ' but ', ' by ', ' can\'t ', ' cannot ', ' could ', ' couldn\'t ', ' did ',
               ' didn\'t ', ' do ', ' does ', ' doesn\'t ', ' doing ', ' don\'t ', ' down ', ' during ', ' each ',
               ' few ', ' for ', ' from ', ' further ', ' had ', ' hadn\'t ', ' has ', ' hasn\'t ', ' have ',
               ' haven\'t ', ' having ', ' he ', ' he\'d ', ' he\'ll ', ' he\'s ', ' her ', ' here ', ' here\'s ',
               ' hers ', ' herself ', ' him ', ' himself ', ' his ', ' how ', ' how\'s ', ' i ', ' i\'d ', ' i\'ll ',
               ' i\'m ', ' i\'ve ', ' if ', ' in ', ' into ', ' is ', ' isn\'t ', ' it ', ' it\'s ', ' its ',
               ' itself ', ' let\'s ', ' me ', ' more ', ' most ', ' mustn\'t ', ' my ', ' myself ', ' no ', ' nor ',
               ' not ', ' of ', ' off ', ' on ', ' once ', ' only ', ' or ', ' other ', ' ought ', ' our ',
               ' ours     ', ' ourselves ', ' out ', ' over ', ' own ', ' same ', ' shan\'t ', ' she ', ' she\'d ',
               ' she\'ll ', ' she\'s ', ' should ', ' shouldn\'t ', ' so ', ' some ', ' such ', ' than ', ' that ',
               ' that\'s ', ' the ', ' their ', ' theirs ', ' them ', ' themselves ', ' then ', ' there ', ' there\'s ',
               ' these ', ' they ', ' they\'d ', ' they\'ll ', ' they\'re ', ' they\'ve ', ' this ', ' those ',
               ' through ', ' to ', ' too ', ' under ', ' until ', ' up ', ' very ', ' was ', ' wasn\'t ', ' we ',
               ' we\'d ', ' we\'ll ', ' we\'re ', ' we\'ve ', ' were ', ' weren\'t ', ' what ', ' what\'s ', ' when ',
               ' when\'s ', ' where ', ' where\'s ', ' which ', ' while ', ' who ', ' who\'s ', ' whom ', ' why ',
               ' why\'s ', ' with ', ' won\'t ', ' would ', ' wouldn\'t ', ' you ', ' you\'d ', ' you\'ll ',
               ' you\'re ', ' you\'ve ', ' your ', ' yours ', ' yourself ', ' yourselves ']
ignoreSigns = ['-', '(', ')', ':', ';', '.', ',', '\\', '/', '?', '!']


def replaceWords(data, words, sign):
    for word in words:
        data = data.replace(word, sign)
    return data


def countOccurrences(word, text):
    return text.lower().split().count(word.lower())


def listOfOccurences(wordList, text):
    list = []
    for word in wordList:
        list.append((word, countOccurrences(word, text)))
    return list


def getValidInputWord():
    try:
        input_from_user = input(
            'Please enter words you want to find - either one word or a list of words seperated by space\n')
        return input_from_user
    except:
        print('Wrong Input, please enter a valid String!')
        return getValidInputWord()

def getValidPhrase():
    try:
        input_from_user = input(
            'Please enter a phrase you want to find\n')
        return input_from_user
    except:
        print('Wrong Input, please enter a valid String!')
        return getValidInputWord()

def getValidFilePath():
    try:
        input_from_user = input('Please enter a path for the txt file\n')
        return input_from_user
    except:
        print('Illegal input, please enter a valid path!')
        return getValidFilePath()


def getValidFolderPath():
    try:
        input_from_user = input('Please enter a folder path:\n')
        # if input_from_user == "":
        #     return
        if os.path.isdir(input_from_user):
            return input_from_user
        else:
            print('This is not a folder, please enter a valid path or press Enter to exit')
            return getValidFolderPath()
    except:
        print('Illegal input, please enter a valid path!')
        return getValidFolderPath()





def receiveWordsToFind():
    listOfWords = []
    print ('dont forget to enter **\'stop\'** to finish')
    inputWord = getValidInputWord()

    while inputWord != -1 and inputWord.lower() != 'stop':
        try:
            if inputWord.index(' ') > 0:
                currentWords = inputWord.replace(" ", " ").split(" ")
                for word in currentWords:
                    listOfWords.append(word)
                inputWord = getValidInputWord()
        except:
            listOfWords.append(inputWord)
            inputWord = getValidInputWord()

    listOfWords = receivePhrasesToFind(listOfWords)
    return listOfWords

def receivePhrasesToFind(listOfWords):
    print ('Now, enter full phrases (full string - for example "Hello World"). dont forget to enter **\'stop\'** to end the script')
    inputPhrase = getValidPhrase()
    while inputPhrase != 'stop':
        listOfWords.append(inputPhrase)
        inputPhrase = getValidPhrase()
    return listOfWords

def removeDups(duplicate):
    final_list = []
    for word in duplicate:
        if word not in final_list:
            final_list.append(word)
    return final_list


def writeToExcelFile(findingsList, file_name, name):
    import openpyxl
    import os
    import datetime

    now = datetime.datetime.now()
    wb = openpyxl.Workbook()
    ws = wb.active
    ws['A1'] = textfile
    ws['A2'] = 'Word'
    ws['B2'] = 'Num Of Occurences'

    # print(findingsList)

    for word in findingsList:
        ws.append(word)

    excel_path_name = "/" + file_name + '-Word-occurences-' + name + "-" + now.strftime('%d-%m-%y-%H%M') + '.xlsx'
    path_to_save = os.getcwd() + excel_path_name
    wb.save(path_to_save)


def openFile():
    text_file_path = getValidFilePath()
    try:
        # with open(text_file_path, 'r') as myfile:
        with codecs.open(text_file_path, "r", encoding='utf-8', errors='ignore') as myfile:
            all_words = myfile.read().replace('\n', ' ')
        return all_words
    except:
        return openFile()

def openFileFromFolder(file):
    return file.read().replace('\n', ' ')


def getWordsList(text):
    return re.compile('\w+').findall(text)


def removeErrorWords(word_list):
    clean_list = []
    for word in word_list:
        if len(word) > 1:
            clean_list.append(word)
    return clean_list

def mainProcessor(all_words, file_name, words_to_find):


    # Clear unnecessary signs and words
    all_words = all_words.lower()
    all_words = replaceWords(all_words,
                             ignoreSigns, ' ')
    all_words = replaceWords(all_words, ignoreWords, ' ')
    # all_words_list = re.findall(r'[^\s!,.?":;0-9]+', all_words)

    all_words_list = getWordsList(all_words)
    [word.lower() for word in all_words_list]
    all_words_list = removeDups(all_words_list)
    all_words_list = removeErrorWords(all_words_list)

    findingsList = listOfOccurences(words_to_find, all_words)
    findingsList.sort(key=lambda tup: tup[1])
    findingsList = list(reversed(findingsList))

    findingslist_unfiltered = listOfOccurences(all_words_list, all_words)
    findingslist_unfiltered.sort(key=lambda tup: tup[1])
    findingslist_unfiltered = list(reversed(findingslist_unfiltered))
    writeToExcelFile(findingslist_unfiltered, file_name,'All')

    writeToExcelFile(findingsList, file_name,'Filtered')



def main():
    user_selection = 0
    while user_selection != 1 and user_selection != 2:
        try:
            user_selection = input("Press 1 to enter file path, and 2 for a folder:\n")
        except:
            continue

    if user_selection == 1:
        file = openFile()
        words_to_find = removeDups(receiveWordsToFind())
        mainProcessor(file, "", words_to_find)
    else:
        folder_path = getValidFolderPath()
        words_to_find = removeDups(receiveWordsToFind())

        files = []
        for file_path in os.listdir(folder_path):
            if file_path.endswith('.txt'):
                file = codecs.open(folder_path+"/"+file_path, "r", encoding='utf-8', errors='ignore')
                files.append(file)

        for file in files:
            file_base_name = os.path.basename(file.name)
            file_base_name = os.path.splitext(file_base_name)[0]
            all_words = openFileFromFolder(file)
            mainProcessor(all_words, file_base_name, words_to_find)


main()


# def nadav():
#     import codecs
#     text_file_path = '/Users/nmiran/Desktop/textfiles/EnglishAll.txt'
#
#     with codecs.open(text_file_path, "r", encoding='utf-8', errors='ignore') as myfile:
#         all_words = myfile.read().replace('\n', ' ')
#
#     return