import os

# textfile = '/Users/nmiran/Desktop/nadav.txt'
textfile = '/Users/nmiran/Desktop/BenGurion.txt'
excelFilePath = '/Users/nmiran/Desktop/nadav.xlsx'
ignoreWords = ['a', ' about ', ' above ', ' after ', ' again ', ' against ', ' all ', ' am ', ' an ', ' and ', ' any ',
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
ignorSigns = ['(', ')', ';', '.', ',', '\\', '/', '?', '!', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                              '0']

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
            'Please enter a words you want to find - either one word or a list of words seperated by space\n')
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


def receiveWordsToFind():
    listOfWords = []

    inputWord = getValidInputWord()

    while inputWord != -1 and inputWord != 'stop':
        try:
            if inputWord.index(' ') > 0:
                currentWords = inputWord.replace(" ", " ").split(" ")
                for word in currentWords:
                    listOfWords.append(word)
                inputWord = getValidInputWord()
        except:
            listOfWords.append(inputWord)
            inputWord = getValidInputWord()

    return listOfWords


def removeDups(duplicate):
    final_list = []
    for word in duplicate:
        if word not in final_list:
            final_list.append(word)
    return final_list


def writeToExcelFile(findingsList):
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

    file_name = '/Word_occurences-' + now.strftime('%d-%m-%y-%H%M') + '.xlsx'
    path_to_save = os.getcwd() + file_name
    wb.save(path_to_save)


def openFile():
    text_file_path = getValidFilePath()
    try:
        # with open(text_file_path, 'r') as myfile:
        import codecs
        with codecs.open(text_file_path, "r", encoding='utf-8', errors='ignore') as myfile:
            all_words = myfile.read().replace('\n', ' ')
        return all_words
    except:
        return openFile()



def main():

    all_words = openFile()

    # Clear unnecessary signs and words
    all_words = all_words.lower()
    all_words = replaceWords(all_words,
                             ignorSigns, '')
    all_words = replaceWords(all_words, ignoreWords, ' ')

    words_to_find = removeDups(receiveWordsToFind())
    findingsList = listOfOccurences(words_to_find, all_words)

    writeToExcelFile(findingsList)

main()
