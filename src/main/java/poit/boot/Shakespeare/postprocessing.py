replacement_terms = ['v.', 't.', 'sub.', 'adj.',
                     'adj,', 'adv.', 'p.', 'i.',
                     ' the ', ' a ', ' an ', ' to ',
                     ' as ', ':', ';', "'", 'pr.',
                     ' ad ', 'ad.', 'j.', ' aa ',
                     ' v ']

replacement_dict = dict()
with open("dictionary.txt", 'r') as file:
    lines = file.readlines()
    for line in lines:
        line_list = line.split(',')
        if line_list[0].isupper():
            cleaned = line_list[1]
            for term in replacement_terms:
                cleaned = cleaned.replace(term, "")
            cleaned = cleaned.strip().split(' ')[0]
            replacement_dict[cleaned.lower()] = line_list[0].lower()

with open("replacements.txt", 'w') as file:
    for key in replacement_dict.keys():
        file.write(key + ', ' + replacement_dict[key] + '\n')
