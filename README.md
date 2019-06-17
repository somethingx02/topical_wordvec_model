# topical_wordvec_model
no out-of-vocabulary word, 3000-8000 vocabulary size, quick in training, suitable for visualization

## Download the yelp dataset

* Go to https://www.yelp.com/dataset/download to download the .7z file

* After extraction, copy the business.json and the review.json to ~/topical_wordvec_models/datasets/

## Preprocessing

* Open terminal in ~/topical_wordvec_models/src/, Run ``python main_yelp_preprocessor.py''

## Training

* Open ~/topical_wordvec_models/datasets/train_params.txt, set the VOCABULARY_SIZE, TRAINING_INSTANCES in ~/topical_wordvec_models/src/settings.py according to it.

* Change the ROOT_DIR = '/data1/zlx2/topical_wordvec_models' in ~/topical_wordvec_models/src/settings.py to your directory.

* Change the on_cuda = True if you wanna run the program on GPU

* Open terminal in ~/topical_wordvec_models/src/, Run ``python train.py''

## Discovered Topics and Word embeddings

### Topics

* Open terminal in ~/topical_wordvec_models/src/, Run ``python main_visualization_of_words_and_topics.py''

* See ~/topical_wordvec_models/datasets/jtwTopicMatrix.txt for the topics.

* You may use ~/topical_wordvec_models/TopicCoherenceComputeJavaProj to compute the topic coherence.

### Word embeddings

* Open terminal in ~/topical_wordvec_models/src/, Run ``python main_compute_wordvec.py''

* See ~/topical_wordvec_models/save/??/aggrd_all_wordrep.txt for the word representations in the format: word 0.??? 0.??? 0.??? ... 0.???